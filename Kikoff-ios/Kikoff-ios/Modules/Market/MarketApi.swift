//
//  MarketApi.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import Foundation
import Combine

final class MarketApi {
    private let session = URLSession.shared
    private let responseQueue = DispatchQueue.main
	
	private let decoder: JSONDecoder = {
		let decoder = JSONDecoder()
		return decoder
	}()
	
    private let tokenProvider = TokenProvider()

	func loadAllCategories() -> AnyPublisher<[MarketCategory], Error> {
//		allCategoriesStub()
		load(request: buildRequest(path: .allCategories(), useToken: false))
	}

    func loadProducts(withCategoryId id: Int) -> AnyPublisher<[MarketProduct], Error> {
//        productsStub()
		load(request: buildRequest(path: .products(forCategory: id), useToken: true))
    }

    private func load<T: Decodable>(request: URLRequest) -> AnyPublisher<T, Error> {
        session
            .dataTaskPublisher(for: request)
            .map(\.data)
            .decode(type: T.self, decoder: decoder)
            .receive(on: responseQueue)
            .eraseToAnyPublisher()
    }
	
	private func buildRequest(path: String, useToken: Bool) -> URLRequest {
        var request = URLRequest(url: .base.appendingPathComponent(path))
		request.httpMethod = "GET"
		if useToken {
			request.setValue(tokenProvider.token, forHTTPHeaderField: "X-TOKEN")
		}

		request.setValue("Application/json", forHTTPHeaderField: "Content-Type")
        return request
    }
}

private extension MarketApi {
    func allCategoriesStub() -> AnyPublisher<[MarketCategory], Error> {
        Future { promise in
            DispatchQueue.global().asyncAfter(deadline: .now() + 1) {
                let stub = (0...10).map { MarketCategory(id: $0, name: "Category \($0)", imageUrl: .imageStub) }
                promise(.success(stub))
            }
        }
        .receive(on: responseQueue)
        .eraseToAnyPublisher()
    }

    func productsStub() -> AnyPublisher<[MarketProduct], Error> {
        Future { promise in
            DispatchQueue.global().asyncAfter(deadline: .now() + 0.4) {
                let stub = (0...20).map {
                    MarketProduct(
                        id: $0,
                        name: "Product \($0)",
                        categoryId: 1,
                        countOfProducts: 20,
                        priceOfOne: 4343,
                        imageUrl: .imageStub
                    )
                }
                promise(.success(stub))
            }
        }
        .receive(on: responseQueue)
        .eraseToAnyPublisher()
    }
}

private extension URL {
    static var base: URL {
        // swiftlint:disable:next force_unwrapping
        URL(string: "http://localhost:8080")!
    }

    static var imageStub: URL? {
        // swiftlint:disable:next force_unwrapping
		return nil
//        URL(string: "https://russianbeauty.ru/wp-content/uploads/2018/03/botox-dlya-grufdi.jpg")!
    }
}

private extension String {
    static func allCategories() -> String {
        "/categories/getAll"
    }

    static func products(forCategory id: Int) -> String {
        "/categories/get/\(id)"
    }
}
