//
//  AuthorizationProvider.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation

protocol IAuthorizationProvider {
    func forgotPassword()
    func createAccount()
    func auth(with model: AuthFormModel)
}

final class AuthorizationProvider: IAuthorizationProvider {
    weak var view: IAuthorizationView?
    private let router: IAuthorizationRouter
    
    init(router: AuthorizationRouter) {
        self.router = router
    }
    
    func forgotPassword() {}
    
    func createAccount() {}
    
    func auth(with model: AuthFormModel) {}
}
