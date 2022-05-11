//
//  UICollectionView+Utils.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import Foundation
import UIKit

extension UICollectionView {
    func registerCell(_ cellTypes: UICollectionViewCell.Type...) {
        cellTypes.forEach { register($0, forCellWithReuseIdentifier: "\($0)") }
    }

    func dequeueCell<T: UICollectionViewCell>(_ type: T.Type, for indexPath: IndexPath) -> T {
        // swiftlint:disable:next force_cast
        dequeueReusableCell(withReuseIdentifier: "\(type)", for: indexPath) as! T
    }

    func registerSupplementaryView(_ view: UICollectionReusableView.Type, of kind: String) {
        register(view, forSupplementaryViewOfKind: kind, withReuseIdentifier: "\(view)")
    }

    func dequeueSupplementaryView<T: UICollectionReusableView>(
        _ view: T.Type,
        of kind: String,
        for indexPath: IndexPath
    ) -> T {
        // swiftlint:disable:next force_cast
        dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "\(view)", for: indexPath) as! T
    }
}
