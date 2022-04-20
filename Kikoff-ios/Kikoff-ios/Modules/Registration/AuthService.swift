//
//  AuthService.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 20.04.2022.
//

import Foundation

final class AuthService {
	let tokenProvider = TokenProvider()
	
    func auth(form: AuthFormModel, completion: @escaping () -> Void) {
		tokenProvider.token = "1234"
        completion()
    }
    
    func send(form: RegistrationForm, completion: @escaping () -> Void) {
		tokenProvider.token = "1234"
        completion()
    }
}
