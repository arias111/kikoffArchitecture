//
//  MarketProductCell.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import UIKit
import Kingfisher

final class MarketProductCell: UICollectionViewCell, Configurable {
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
        label.font = .systemFont(ofSize: 16, weight: .regular)
        return label
    }()

    private lazy var priceLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 16, weight: .regular)
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

    func configure(with item: MarketProduct) -> Self {
        titleLabel.text = item.name
        priceLabel.text = "₽ \(item.priceOfOne)"
        imageView.kf.setImage(with: item.imageUrl)
        return self
    }

    // MARK: Initial Configuration

    private func setupView() {
        contentView.addSubview(imageView) {
            $0.height.leading.equalToSuperview()
            $0.width.equalTo(contentView.snp.height)
        }

        contentView.addSubview(titleLabel) {
            $0.top.equalTo(imageView.snp.top)
            $0.leading.equalTo(imageView.snp.trailing).offset(16)
            $0.trailing.equalToSuperview()
        }

        contentView.addSubview(priceLabel) {
            $0.leading.equalTo(imageView.snp.trailing).offset(16)
            $0.top.equalTo(titleLabel.snp.bottom).offset(16)
            $0.trailing.equalToSuperview().inset(16)
        }
    }
}
