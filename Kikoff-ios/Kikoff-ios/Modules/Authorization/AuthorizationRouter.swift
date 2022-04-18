//
//  AuthorizationRouter.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

protocol IAuthorizationRouter {}

final class AuthorizationRouter: IAuthorizationRouter {
    weak var view: UIViewController?
    
    var navigationController: UINavigationController? {
        view?.navigationController
    }
}
