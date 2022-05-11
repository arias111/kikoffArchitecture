//
//  CollectionHeaderFooterView.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import UIKit

final class CollectionHeaderFooterView: UICollectionReusableView, Configurable {
    typealias Item = String

    private let insets = UIEdgeInsets(top: 8, left: 0, bottom: 8, right: 0)

    // MARK: Subviews

    private lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 18, weight: .medium)
        return label
    }()

    // MARK: Init

    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Updating

    @discardableResult
    func configure(with item: String) -> Self {
        titleLabel.text = item
        return self
    }

    // MARK: Helpers
    
    private func setup() {
        addSubview(titleLabel)
        titleLabel.snp.makeConstraints {
            $0.edges.equalToSuperview().inset(insets)
        }
    }
}
