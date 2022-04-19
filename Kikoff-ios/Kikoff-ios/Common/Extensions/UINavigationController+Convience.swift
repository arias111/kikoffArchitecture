//
//  UINavigationController+Convience.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

extension UINavigationController {
    convenience init(navigationBar: UINavigationBar.Type, rootViewController: UIViewController) {
        self.init(navigationBarClass: navigationBar, toolbarClass: nil)
        pushViewController(rootViewController, animated: false)
    }
}
