//
//  UIView+SnapkitUtils.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit
import SnapKit

extension UIView {
    func addSubview(_ subview: UIView, _ makeBlock: (ConstraintMaker) -> Void) {
        addSubview(subview)
        subview.snp.makeConstraints(makeBlock)
    }
}
