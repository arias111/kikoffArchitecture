//
//  AuthorizationViewController.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

protocol IAuthorizationView: AnyObject {}

final class AuthorizationViewController: UIViewController, IAuthorizationView {
    private let provider: IAuthorizationProvider
    
    init(provider: IAuthorizationProvider) {
        self.provider = provider
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
