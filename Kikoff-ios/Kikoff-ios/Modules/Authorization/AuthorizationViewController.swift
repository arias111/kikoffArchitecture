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
    
    private let service = AuthService()
    
    // MARK: Subviews
    
    private lazy var form = AuthorizationForm()
    
    // MARK: Life Cycle
    
    override func loadView() {
        view = RootView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationItem()
        setupView()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        form.reset()
    }
    
    // MARK: Helpers
    
    private func setupNavigationItem() {
        title = "Authorization"
        navigationItem.backButtonTitle = ""
    }
    
    private func setupView() {
        rootView.backgroundColor = .socialWhite
        rootView.set(form)
        
        form.onAuthTapped { [unowned self] in auth(model: $0) }
        form.onCreateAccountTapped { [unowned self] in createTapped() }
    }
    
    private func auth(model: AuthFormModel) {
		service.auth(form: model) { [weak self] result in
			switch result {
			case .success:
				DispatchQueue.main.async {
					self?.showProfile()
				}
			case .failure:
				DispatchQueue.main.async {
					let alert = UIAlertController(
						title: "Something went wrong",
						message: nil,
						preferredStyle: .alert
					)
					let action = UIAlertAction(title: "Try again", style: .default, handler: nil)
					alert.addAction(action)
					self?.navigationController?.present(alert, animated: true, completion: nil)
				}
			}
        }
    }
    
    private func createTapped() {
        let controller = UsernamePassStepViewController()
        navigationController?.pushViewController(controller, animated: true)
    }
    
    private func showProfile() {
		let profileVc = ProfileViewController()
		navigationController?.pushViewController(profileVc, animated: true)
	}
}
