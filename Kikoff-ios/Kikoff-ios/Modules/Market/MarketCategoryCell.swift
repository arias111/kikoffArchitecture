//
//  MarketCategoryCell.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import UIKit
import Kingfisher

final class MarketCategoryCell: UICollectionViewCell, Configurable {
    // MARK: Subviews

    private lazy var imageView: UIImageView = {
        let imageView = UIImageView()
        imageView.layer.cornerRadius = 10
        imageView.layer.masksToBounds = true
        imageView.clipsToBounds = true
        imageView.contentMode = .scaleAspectFill
        imageView.kf.indicatorType = .activity
        return imageView
    }()

    private lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 14, weight: .regular)
		label.textAlignment = .center
        return label
    }()

    // MARK: Init

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Internal Functions

    func configure(with item: MarketCategory) -> Self {
        titleLabel.text = item.name
        imageView.kf.setImage(with: item.imageUrl, placeholder: UIImage(named: "profile"))
        return self
    }

    // MARK: Initial Configuration

    private func setupView() {
        contentView.addSubview(imageView) {
            $0.top.width.equalToSuperview()
            $0.height.equalTo(contentView.snp.width)
        }

        contentView.addSubview(titleLabel) {
            $0.top.equalTo(imageView.snp.bottom).offset(8)
            $0.leading.trailing.bottom.equalToSuperview()
        }
    }
}
