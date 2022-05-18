//
//  BasketService.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.05.2022.
//

import Foundation

struct Address: Encodable {
    let flat: Int
    let houseNumber: String
    let street: String
}

final class BasketService {
    static let shared = BasketService()
    private let session = URLSession.shared
    private var products: [MarketProduct] = []
    private let decoder = JSONDecoder()
    private var basketId: Int?
    private let tokenProvider = TokenProvider()

    private init() {}
    
    func addToCard(product: MarketProduct) {
        products.append(product)
    }
    
    func buy(address: Address, completion: @escaping (Result<Void, Error>) -> Void) {
        Task {
            do {
                try await handleBuying(address: address)
                
                DispatchQueue.main.async {
                    completion(.success)
                }
                print("success buying")
            } catch {
                print("error: \(error), file: \(#file), function: \(#function)")
                DispatchQueue.main.async {
                    completion(.failure(error))
                }
            }
        }
    }
    
    // Бек - говно, я - молодец
    private func handleBuying(address: Address) async throws {
        let products = self.products
        // достаем профиль
        let (profileData, _) = try await session.data(for: .getProfile())
        let profile = try decoder.decode(Profile.self, from: profileData)
        // создаем корзину
        let (baskedData, _) = try await session.data(for: .createBasket(products: products, userId: profile.id))
        let basket = try decoder.decode(BasketResponse.self, from: baskedData)
        // сохраняем адрес доставки
        let (deliveryData, _) = try await session.data(for: .createDelivery(address: address))
        let addressId = try decoder.decode(Delivery.self, from: deliveryData).addressId
        // создаем чек
        let (billCreateData, _) = try await session.data(for: .billCreate(addressId: addressId, personalAccountId: profile.personalAccountId))
        let billId = try decoder.decode(BillCreateResponse.self, from: billCreateData).id
        // заполняем чек продуктами
        _ = try await session.data(for: .billFill(billId: billId, productList: products))
        // оплачиваем чек
        _ = try await session.data(for: .billSuccess(billId: billId))
        // удаляем корзину
        _ = try await session.data(for: .deleteBasket(basketId: basket.basketId))
        
        // очищаем кеш
        self.products.removeAll()
    }
}

private extension URLRequest {
    // MARK: Bill
    
    static func billCreate(addressId: Int, personalAccountId: Int) -> URLRequest {
        let url = URL(string: "http://localhost:8080/bill/create")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        let json = ["addressId": addressId, "personalAccountId": personalAccountId]
        request.httpBody = try? JSONSerialization.data(withJSONObject: json)
        return request
    }
    
    static func billFill(billId: Int, productList: [MarketProduct]) -> URLRequest {
        let url = URL(string: "http://localhost:8080/bill/fill")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        request.httpBody = try? JSONEncoder().encode(BillFillRequest(billId: billId, productList: productList))
        return request
    }
    
    static func billSuccess(billId: Int) -> URLRequest {
        let url = URL(string: "http://localhost:8080/bill/fill")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        let json = ["id": billId]
        request.httpBody = try? JSONSerialization.data(withJSONObject: json)
        return request
    }
    
    // MARK: Delivery
    
    static func createDelivery(address: Address) -> URLRequest {
        let url = URL(string: "http://localhost:8080/delivery")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        request.httpBody = try? JSONEncoder().encode(address)
        return request
    }
    
    // MARK: Profile
    
    static func getProfile() -> URLRequest {
        let url = URL(string: "http://localhost:8080/profile")!
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        return request
    }
    
    // MARK: Basket
    
    static func createBasket(products: [MarketProduct], userId: Int) -> URLRequest {
        let url = URL(string: "http://localhost:8080/basket/createBasket")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        request.httpBody = try? JSONEncoder().encode(CreateBasketRequest(productList: products, userId: userId))
        return request
    }
    
    static func deleteBasket(basketId: Int) -> URLRequest {
        let url = URL(string: "http://localhost:8080/basket/deleteBasket")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(TokenProvider().token, forHTTPHeaderField: "X-TOKEN")
        let json = ["basketId": basketId]
        
        return request
    }
}

private struct CreateBasketRequest: Encodable {
    let productList: [MarketProduct]
    let userId: Int
}

private struct BasketResponse: Decodable {
    let basketId: Int
}

extension Result where Success == Void {
    static var success: Result<Void, Failure> { .success(()) }
}

private struct Profile: Decodable {
    let id: Int
    let personalAccountId: Int
}

private struct Delivery: Decodable {
    let addressId: Int
}

private struct BillCreateResponse: Decodable {
    let id: Int
}

private struct BillFillRequest: Encodable {
    let billId: Int
    let productList: [MarketProduct]
}
