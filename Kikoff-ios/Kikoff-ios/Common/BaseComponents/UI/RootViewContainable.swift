//
//  CustomViewContainable.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

protocol RootViewContainable {
    associatedtype RootView: UIView
    var rootView: RootView { get }
}

extension RootViewContainable where Self: UIViewController {
    var rootView: RootView {
        // swiftlint:disable:next force_cast
        view as! RootView
    }
}
