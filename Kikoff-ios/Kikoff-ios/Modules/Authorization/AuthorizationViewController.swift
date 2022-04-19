//
//  AuthorizationViewController.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

protocol IAuthorizationView: AnyObject {}

final class AuthorizationViewController: UIViewController, RootViewContainable, IAuthorizationView {
    typealias RootView = ScrollableStackView
    
    private let provider: IAuthorizationProvider
    
    // MARK: Subviews
    
    private lazy var form = AuthorizationForm()
    
    // MARK: Init
    
    init(provider: IAuthorizationProvider) {
        self.provider = provider
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Life Cycle
    
    override func loadView() {
        view = RootView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationItem()
        setupView()
    }
    
    // MARK: Helpers
    
    private func setupNavigationItem() {
        title = "Authorization"
    }
    
    private func setupView() {
        rootView.backgroundColor = .socialWhite
        rootView.set(form)
        
        form.onAuthTapped { [provider] model in
            provider.auth(with: model)
        }
        
        form.onCreateAccountTapped { [provider] in
            provider.createAccount()
        }
    }
}
