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
		customView.delegate = self
    }
	
	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		customView.reloadData()
	}
	
	func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
		return basketService.products.count
	}
	
	func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
		guard let cell = collectionView.dequeueReusableCell(
			withReuseIdentifier: "MarketProductCell",
			for: indexPath
		) as? MarketProductCell
		else { return UICollectionViewCell() }
		let cells = basketService.products[indexPath.row]
		cell.configure(with: cells)
		return cell
	}
}

extension BasketViewController: BasketDelegate {
	func checkOut(address: Address) {
		basketService.buy(address: address) { result in
			self.customView.reloadData()
			self.navigationController?.popToRootViewController(animated: true)
		}
	}
}
