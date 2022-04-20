//
//  StylingButton.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 19.04.2022.
//

import UIKit

final class StylingButton: TappableButton {
    struct Style {
        let title: String
        let backgroundColor: UIColor?
        let titleColor: UIColor?
        let titleFont: UIFont?
        let cornerRadius: CGFloat?
        
        init(
            title: String,
            backgroundColor: UIColor? = nil,
            titleColor: UIColor? = nil,
            titleFont: UIFont? = nil,
            cornerRadius: CGFloat? = nil
        ) {
            self.title = title
            self.backgroundColor = backgroundColor
            self.titleColor = titleColor
            self.titleFont = titleFont
            self.cornerRadius = cornerRadius
        }
    }
    
    convenience init(style: Style, onTap: VoidClosure? = nil) {
        self.init(frame: .zero)
        apply(style: style)
        onTap.map(enableTapping(_:))
    }
    
    func apply(style: Style) {
        setTitle(style.title, for: .normal)
        style.backgroundColor.map { backgroundColor = $0 }
        style.titleColor.map { setTitleColor($0, for: .normal) }
        style.titleFont.map { titleLabel?.font = $0 }
        style.cornerRadius.map { layer.cornerRadius = $0 }
    }
}

extension StylingButton.Style {
    static func primary(title: String) -> Self {
        .init(title: title, backgroundColor: .pureBlack, cornerRadius: 5)
    }
    
    static func suggestion(title: String) -> Self {
        .init(title: title, titleColor: .pureBlack, titleFont: .systemFont(ofSize: 12, weight: .light))
    }
}
