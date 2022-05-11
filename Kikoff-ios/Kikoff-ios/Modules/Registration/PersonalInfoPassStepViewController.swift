//
//  PersonalInfoPassStepViewController.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 19.04.2022.
//

import Foundation
import UIKit

final class PersonalInfoPassStepViewController: UIViewController, RootViewContainable {
    typealias RootView = ScrollableStackView
    
    private let previousStep: UsernamePassStep
    private let service = AuthService()
    
    // MARK: Subviews
    
    private lazy var firstNameField = StylingField(style: .auth(placeholder: "First name"))
    private lazy var lastNameField = StylingField(style: .auth(placeholder: "Last name"))
    private lazy var patronymicField = StylingField(style: .auth(placeholder: "Patronymic"))
    
    private lazy var dateLabel: UILabel = {
        let label = UILabel()
        label.text = "BirthDate:"
        label.font = .systemFont(ofSize: 20, weight: .regular)
        label.textColor = .darkGray
        return label
    }()
    
    private lazy var datePicker: UIDatePicker = {
        let picker = UIDatePicker()
        picker.datePickerMode = .date
        picker.maximumDate = Date()
        picker.preferredDatePickerStyle = .compact
        return picker
    }()
    
    private lazy var doneButton = StylingButton(style: .primary(title: "Done")) { [unowned self] in send() }
    
    // MARK: Init
    
    init(previousStep: UsernamePassStep) {
        self.previousStep = previousStep
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Life Cycle
    
    override func loadView() {
        view = ScrollableStackView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationItem()
        setupView()
    }
    
    // MARK: Helpers
    
    private func setupNavigationItem() {
        title = "Enter personal info"
        navigationItem.backButtonTitle = ""
    }
    
    private func setupView() {
        rootView.backgroundColor = .primaryWhite
        
        let birthStack = UIStackView(dateLabel, datePicker)
            .axis(.horizontal)
        
        rootView.set(firstNameField, lastNameField, patronymicField, birthStack, doneButton)
    }
    
    private func send() {
        guard
            let firstName = firstNameField.nonEmptyText,
            let lastName = lastNameField.nonEmptyText,
            let patronymic = patronymicField.nonEmptyText
        else { return }
		let dateFormatter = DateFormatter()

        let form = RegistrationModel(
			birthday: dateFormatter.string(from: datePicker.date),
			creationDate: dateFormatter.string(from: Date()),
			email: previousStep.username,
			firstName: firstName,
			lastName: lastName,
			password: previousStep.password,
			patronymic: patronymic
        )
        
		service.send(form: form) { [weak self] in
			self?.navigationController?.setViewControllers([AuthorizationViewController()], animated: true)
		}
    }
}

// MARK: - UITextFieldDelegate

extension PersonalInfoPassStepViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case firstNameField:
            lastNameField.becomeFirstResponder()
        case lastNameField:
            patronymicField.becomeFirstResponder()
        default:
            textField.resignFirstResponder()
        }
        
        return true
    }
}
