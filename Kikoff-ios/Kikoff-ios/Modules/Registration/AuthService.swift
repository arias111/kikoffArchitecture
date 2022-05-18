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
		let url = String(format: "http://localhost:8080/signIn")
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
					if let token = (json as? [String: String])?["token"], !token.isEmpty {
						self.tokenProvider.token = token
						completion(.success(()))
					} else {
						completion(.failure(.decoding))
					}
				} catch {
					completion(.failure(.fetchingData))
				}
			}
		}
		.resume()
	}
    
    func send(form: RegistrationModel, completion: @escaping () -> Void) {
		let url = String(format: "http://localhost:8080/signUp")
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
					let token = (json as? [String: String])?["token"]
					self.tokenProvider.token = token
					let userData = ["email": form.email, "name": form.firstName, "lastName": form.lastName]
					UserDefaults.standard.set(userData, forKey: "model")
				} catch {
					print(error)
				}
			}
		}
		.resume()
	}
}
