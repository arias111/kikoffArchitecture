//
//  AuthorizationProvider.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation

protocol IAuthorizationProvider {}

final class AuthorizationProvider: IAuthorizationProvider {
    weak var view: IAuthorizationView?
    private let router: IAuthorizationRouter
    
    init(router: AuthorizationRouter) {
        self.router = router
    }
}
