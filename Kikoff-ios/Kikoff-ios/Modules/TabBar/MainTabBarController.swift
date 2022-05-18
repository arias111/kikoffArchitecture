//
//  MainTabBarController.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 12.05.2022.
//

import UIKit

final class MainTabBarController: UITabBarController {
	override func viewDidLoad() {
		super.viewDidLoad()
		setupScreens()
	}
	
	private func setupScreens() {
		let profile = ProfileViewController()
		let market = MarketViewController()
		let basket = BasketViewController()
		
		viewControllers = [market, basket, profile]
		tabBarItem.image = UIImage(named: "profile")
		selectedIndex = 1
	}
}
