//
//  Configurable.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import Foundation

protocol Configurable {
    associatedtype Item
    @discardableResult
    func configure(with item: Item) -> Self
}
