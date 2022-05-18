//
//  ProductViewController.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 18.05.2022.
//

import Foundation
import UIKit
import Kingfisher

final class ProductViewController: UIViewController, RootViewContainable {
    typealias RootView = ScrollableStackView

    private let product: MarketProduct

    init(product: MarketProduct) {
        self.product = product
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = ScrollableStackView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureView()
    }

    private func configureView() {
        title = product.name

        rootView.backgroundColor = .white
        
        let imageView = UIImageView()
        imageView.kf.setImage(with: product.imageUrl)
        imageView.snp.makeConstraints { make in
            make.size.equalTo(CGSize(width: 100, height: 100))
        }

        rootView.set(imageView)

        let nameLabel = UILabel()
        nameLabel.font = .systemFont(ofSize: 14, weight: .semibold)
        nameLabel.setContentCompressionResistancePriority(.defaultHigh, for: .vertical)
        rootView.set(nameLabel)

        let priceLabel = UILabel()
        priceLabel.font = .systemFont(ofSize: 24, weight: .regular)
        priceLabel.text = "Price: ₽ \(product.priceOfOne)"
        rootView.set(priceLabel)

        let button = StylingButton(style: .primary(title: "Add to Cart"))
        button.enableTapping { [unowned self] in
            showBasket()
        }
        
        rootView.addSubview(button) {
            $0.leading.trailing.bottom.equalToSuperview().inset(40)
        }
    }
    
    private func showBasket() {
		BasketService.shared.addToCard(product: product)
		navigationController?.pushViewController(BasketViewController(), animated: true)
	}
}
