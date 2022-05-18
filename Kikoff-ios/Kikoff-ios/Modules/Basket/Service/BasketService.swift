//
//  BasketService.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 18.05.2022.
//

import Foundation

struct BasketProduct: Codable {
	let categoryId: Int?
	let countOfProducts: Int?
	let name: String?
	let priceOfOne: Int?
	let url: String?
}

struct Basket: Codable {
	let products: [BasketProduct]
}

class BasketService {
	private let tokenProvider = TokenProvider()
	static let shared = BasketService()
	var products: [MarketProduct] = []
	
	private init() { }

	func getCart(completion: @escaping (Result<Basket, CustomError>) -> Void) {
		guard let id = tokenProvider.basketId else { return }
		guard let url = URL(string: "http://localhost:8080/payment/getBalance/\(id)") else { return }
		var request = URLRequest(url: url)
		request.httpMethod = "GET"
		request.setValue(tokenProvider.token, forHTTPHeaderField: "X-TOKEN")
		URLSession.shared.dataTask(with: request) { data, _, _ in
			if let data = data {
				do {
					let decoder = JSONDecoder()
					let cart = try decoder.decode(Basket.self, from: data)
					completion(.success(cart))
				} catch {
					completion(.failure(.fetchingData))
				}
			} else {
				completion(.failure(.fetchingData))
			}
		}
		.resume()
	}
	
	func addToBasket(product: MarketProduct) {
		products.append(product)
	}
	
	func buy(completion: @escaping () -> Void) {		
	}
	
	func createBasket(products: [MarketProduct], completion: @escaping () -> Void) {
		let session2 = URLSession.shared
		guard let urls = URL(string: "http://localhost:8080/basket/createBasket") else { return }
		var request2 = URLRequest(url: urls)
		request2.httpMethod = "POST"
		request2.setValue(tokenProvider.token, forHTTPHeaderField: "X-TOKEN")
		session2.dataTask(with: urls) { data, _, error in
			if let data = data {
				do {
					let json = try JSONSerialization.jsonObject(with: data, options: [])
					let basketId = (json as? [String: String])?["basketId"]
					self.tokenProvider.basketId = basketId
				} catch {
					print(error)
				}
			}
		}
		.resume()
	}
}
