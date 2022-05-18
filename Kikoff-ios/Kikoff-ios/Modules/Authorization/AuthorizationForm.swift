//
//  AuthorizationForm.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

struct AuthFormModel: Encodable {
    let email: String
    let password: String
}

final class AuthorizationForm: UIView {
    // MARK: Subviews
    
    private lazy var usernameField = StylingField(style: .auth(placeholder: "Username"))
    private lazy var passwordField = StylingField(style: .auth(placeholder: "Password"))
    private lazy var authButton = StylingButton(style: .primary(title: "Authorize"))
    private lazy var createAccountButton = StylingButton(style: .suggestion(title: "CREATE ACCOUNT"))

    // MARK: Init
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupLayout()
        setDelegates()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Observing
    
    func onCreateAccountTapped(_ onTap: @escaping VoidClosure) {
        createAccountButton.enableTapping(onTap)
    }
    
    func onAuthTapped(_ onTap: @escaping (AuthFormModel) -> Void) {
        authButton.enableTapping { [unowned self] in
            guard
                let email = usernameField.nonEmptyText,
                let password = passwordField.nonEmptyText
            else { return }
            
            onTap(AuthFormModel(email: email, password: password))
        }
    }
    
    func reset() {
        usernameField.text = nil
        passwordField.text = nil
    }
    
    // MARK: Helpers
    
    private func setupLayout() {
        let stack = UIStackView(usernameField, passwordField, authButton, createAccountButton)
            .axis(.vertical)
            .spacing(.spacing)
        
        addSubview(stack) {
            $0.edges.equalToSuperview()
        }
    }
    
    private func setDelegates() {
        usernameField.delegate = self
        passwordField.delegate = self
    }
}

// MARK: - AuthorizationForm + UITextFieldDelegate

extension AuthorizationForm: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case usernameField:
            passwordField.becomeFirstResponder()
        default:
            textField.resignFirstResponder()
        }
        
        return true
    }
}

private extension CGFloat {
    static let spacing: CGFloat = 15
}
