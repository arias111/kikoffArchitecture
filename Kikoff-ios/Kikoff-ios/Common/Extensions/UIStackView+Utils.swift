//
//  UIStackView+Utils.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

extension UIStackView {
    convenience init(_ arrangedSubviews: UIView...) {
        self.init(arrangedSubviews: arrangedSubviews)
    }

    @discardableResult
    func axis(_ axis: NSLayoutConstraint.Axis) -> Self {
        self.axis = axis
        return self
    }

    @discardableResult
    func alignment(_ alignment: Alignment) -> Self {
        self.alignment = alignment
        return self
    }

    @discardableResult
    func spacing(_ spacing: CGFloat) -> Self {
        self.spacing = spacing
        return self
    }

    @discardableResult
    func distribution(_ distribution: Distribution) -> Self {
        self.distribution = distribution
        return self
    }
}
