//
//  UITextField+Utils.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 20.04.2022.
//

import Foundation
import UIKit

extension UITextField {
    var nonEmptyText: String? {
        hasText ? text : nil
    }
}
