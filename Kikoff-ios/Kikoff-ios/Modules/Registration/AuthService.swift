//
//  AuthService.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 20.04.2022.
//

import Foundation

enum CustomError: Error {
	case statusCode
	case decoding
	case fetchingData
	case other(Error)
}

final class AuthService {
	let tokenProvider = TokenProvider()
	
	func auth(form: AuthFormModel, completion: @escaping (Result<Void, CustomError>) -> Void) {
		let url = String(format: "http://localhost:8081/sign-in")
		guard let serviceUrl = URL(string: url) else { return }
		
		let encodableModel = try? JSONEncoder().encode(form)
		var request = URLRequest(url: serviceUrl)
		request.httpMethod = "POST"
		request.setValue("Application/json", forHTTPHeaderField: "Content-Type")
		guard let httpBody = encodableModel
		else { return }
		request.httpBody = httpBody
		let session = URLSession.shared
		session.dataTask(with: request) { data, response, _ in
			if let httpResponse = response as? HTTPURLResponse, case 400...450 = httpResponse.statusCode {
				completion(.failure(.statusCode))
			}
			if let data = data {
				do {
					let json = try JSONSerialization.jsonObject(with: data, options: [])
					print(json)
					completion(.success(()))
				} catch {
					completion(.failure(.fetchingData))
				}
			}
		}
		.resume()
	}
    
    func send(form: RegistrationModel, completion: @escaping () -> Void) {
		let url = String(format: "http://localhost:8081/sign-up")
		guard let serviceUrl = URL(string: url) else { return }
		
		let encodableModel = try? JSONEncoder().encode(form)
		var request = URLRequest(url: serviceUrl)
		request.httpMethod = "POST"
		request.setValue("Application/json", forHTTPHeaderField: "Content-Type")
		guard let httpBody = encodableModel
		else { return }
		request.httpBody = httpBody
		let session = URLSession.shared
		session.dataTask(with: request) { data, _, error in
			if let data = data {
				do {
					let json = try JSONSerialization.jsonObject(with: data, options: [])
					print(json)
				} catch {
					print(error)
				}
			}
		}
		.resume()
        completion()
    }
}
