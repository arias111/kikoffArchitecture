//
//  SceneDelegate.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
	let tokenProvider = TokenProvider()

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = scene as? UIWindowScene else { return }
        window = UIWindow(windowScene: windowScene)
        
		let viewController: UIViewController = AuthorizationViewController()
//		if tokenProvider.token == nil {
//			viewController = AuthorizationViewController()
//		} else {
//			viewController = ProfileViewController()
//		}
        
        window?.rootViewController = UINavigationController(
            navigationBar: NavigationBar.self,
            rootViewController: viewController
        )
        window?.makeKeyAndVisible()
    }
}
