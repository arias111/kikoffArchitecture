//
//  ProfileService.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 18.05.2022.
//

import Foundation

class ProfileService {
	private let tokenProvider = TokenProvider()
	
	func getBalance(completion: @escaping (Result<String, CustomError>) -> Void) {
		guard let url = URL(string: "http://localhost:8080/payment/getBalance") else { return }
		var request = URLRequest(url: url)
		request.httpMethod = "GET"
		request.setValue(tokenProvider.token, forHTTPHeaderField: "X-TOKEN")
		URLSession.shared.dataTask(with: request) { data, _, _ in
			if let data = data {
				do {
					let json = String(data: data, encoding: .utf8)
					guard let balance = Int(json ?? "") else { return completion(.failure(.fetchingData)) }
					completion(.success(json ?? ""))
				} catch {
					completion(.failure(.fetchingData))
				}
			} else {
				completion(.failure(.fetchingData))
			}
		}
		.resume()
	}
}
