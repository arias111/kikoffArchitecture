//
//  StylingField.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

final class StylingField: InsettingTextField {
    struct Style {
        let placeholder: String?
        let text: String?
        let isSecure: Bool?
        let borderWidth: CGFloat?
        let borderColor: UIColor?
        let cornerRadius: CGFloat?
        let insets: UIEdgeInsets?
        let autocorrection: UITextAutocorrectionType?
        let clearMode: UITextField.ViewMode?
        
        init(
            placeholder: String? = nil,
            text: String? = nil,
            isSecure: Bool? = nil,
            borderWidth: CGFloat? = nil,
            borderColor: UIColor? = nil,
            cornerRadius: CGFloat? = nil,
            insets: UIEdgeInsets? = nil,
            autocorrection: UITextAutocorrectionType? = nil,
            clearMode: UITextField.ViewMode? = nil
        ) {
            self.placeholder = placeholder
            self.text = text
            self.isSecure = isSecure
            self.borderWidth = borderWidth
            self.borderColor = borderColor
            self.cornerRadius = cornerRadius
            self.insets = insets
            self.autocorrection = autocorrection
            self.clearMode = clearMode
        }
    }
    
    convenience init(style: Style) {
        self.init(frame: .zero)
        apply(style: style)
    }
    
    func apply(style: Style) {
        style.placeholder.map { placeholder = $0 }
        style.text.map { text = $0 }
        style.isSecure.map { isSecureTextEntry = $0 }
        style.borderWidth.map { layer.borderWidth = $0 }
        style.borderColor.map { layer.borderColor = $0.cgColor }
        style.cornerRadius.map { layer.cornerRadius = $0 }
        style.insets.map { textInsets = $0 }
        style.autocorrection.map { autocorrectionType = $0 }
        style.clearMode.map { clearButtonMode = $0 }
    }
}

// MARK: - Styles

extension StylingField.Style {
    static var username: Self {
        auth(placeholder: "Username")
    }
    
    static var password: Self {
        auth(placeholder: "Password", isSecure: true)
    }
    
    private static func auth(placeholder: String, isSecure: Bool = false) -> Self {
        .init(
            placeholder: placeholder,
            isSecure: isSecure,
            borderWidth: 1,
            borderColor: .pureBlack.withAlphaComponent(0.5),
            cornerRadius: 5,
            insets: UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8),
            autocorrection: .no,
            clearMode: .whileEditing
        )
    }
}
