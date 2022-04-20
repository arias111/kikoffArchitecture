//
//  InsettingTextField.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

class InsettingTextField: UITextField {
    var textInsets = UIEdgeInsets.zero {
        didSet {
            setNeedsDisplay()
        }
    }

    convenience init(insets: UIEdgeInsets) {
        self.init(frame: .zero)
        self.textInsets = insets
    }

    override func textRect(forBounds bounds: CGRect) -> CGRect {
        return bounds.inset(by: textInsets)
    }

    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        return bounds.inset(by: textInsets)
    }

    override func placeholderRect(forBounds bounds: CGRect) -> CGRect {
        return bounds.inset(by: textInsets)
    }
//    override func drawText(in rect: CGRect) {
//        super.drawText(in: rect.inset(by: textInsets))
//    }
}
