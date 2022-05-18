//
//  BasketViewController.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 17.05.2022.
//

import UIKit

class BasketViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {
	private let customView = BasketView()
	private let basketService = BasketService.shared
	override func loadView() {
		self.view = customView
	}

    override func viewDidLoad() {
        super.viewDidLoad()
		title = "Your Cart"
		customView.collectionView.delegate = self
		customView.collectionView.dataSource = self
//		basketService.getCart { result in
//			switch result {
//			case .success(let basket):
//				print(basket)
//			case .failure(let error):
//				print(error)
//			}
//		}
    }
	
	func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
		return 10
	}
	
	func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
		guard let cell = collectionView.dequeueReusableCell(
			withReuseIdentifier: "MarketProductCell",
			for: indexPath
		) as? MarketProductCell
		else { return UICollectionViewCell() }
		let stub = (0...20).map {
			MarketProduct(
				id: $0,
				name: "Product \($0)",
				categoryId: 1,
				countOfProducts: 20,
				priceOfOne: 4343,
				imageUrl: URL(string: "")
			)
		}
		let cells = stub[indexPath.row]
		cell.configure(with: cells)
		return cell
	}
}
