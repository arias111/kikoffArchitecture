//
//  AuthorizationAssembly.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

enum AuthorizationAssembly {
    static func assemble() -> UIViewController {
        let router = AuthorizationRouter()
        let provider = AuthorizationProvider(router: router)
        let view = AuthorizationViewController(provider: provider)
        router.view = view
        provider.view = view
        
        return view
    }
}
