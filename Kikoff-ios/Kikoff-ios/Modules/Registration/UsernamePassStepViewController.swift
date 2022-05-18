//
//  UsernamePassStepViewController.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 19.04.2022.
//

import Foundation
import UIKit

struct UsernamePassStep {
    let username: String
    let password: String
}

final class UsernamePassStepViewController: UIViewController, RootViewContainable {
    typealias RootView = ScrollableStackView
    
    // MARK: Subviews
    
    private lazy var usernameField = StylingField(style: .auth(placeholder: "Username"))
    private lazy var passwordField = StylingField(style: .auth(placeholder: "Password", isSecure: true))
    private lazy var confirmPasswordField = StylingField(style: .auth(placeholder: "Confirm password", isSecure: true))
    private lazy var doneButton = StylingButton(style: .primary(title: "Confirm"))
    
    private var fields: [UITextField] {
        [usernameField, passwordField, confirmPasswordField]
    }
    
    // MARK: LifeCycle
    
    override func loadView() {
        view = ScrollableStackView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationItem()
        setupView()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        fields.forEach { $0.text = nil }
    }
    
    // MARK: Helpers
    
    private func setupNavigationItem() {
        title = "Registration"
        navigationItem.backButtonTitle = ""
    }
    
    private func setupView() {
        rootView.backgroundColor = .socialWhite
        rootView.set(usernameField, passwordField, confirmPasswordField, doneButton)
        fields.forEach { $0.delegate = self }
        doneButton.enableTapping { [unowned self] in
            confirmTapped()
        }
    }
    
    private func confirmTapped() {
        guard
            let username = usernameField.nonEmptyText,
            let password = passwordField.nonEmptyText,
            password == confirmPasswordField.nonEmptyText
        else {
            return
        }
        
        let step = UsernamePassStep(username: username, password: password)
        let controller = PersonalInfoPassStepViewController(previousStep: step)
        navigationController?.pushViewController(controller, animated: true)
    }
}

// MARK: - UITextFieldDelegate

extension UsernamePassStepViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case usernameField:
            passwordField.becomeFirstResponder()
        case passwordField:
            confirmPasswordField.becomeFirstResponder()
        default:
            textField.resignFirstResponder()
        }
        return true
    }
}
