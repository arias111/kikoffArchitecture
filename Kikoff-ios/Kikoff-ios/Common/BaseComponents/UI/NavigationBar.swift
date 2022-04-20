//
//  NavigationBar.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import UIKit

final class NavigationBar: UINavigationBar {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupView() {
        prefersLargeTitles = true
        tintColor = .primaryBlack

        let appearance = UINavigationBarAppearance()
        appearance.backgroundColor = .socialWhite
        appearance.shadowColor = .clear
        appearance.largeTitleTextAttributes = [.foregroundColor: UIColor.primaryBlack]
        appearance.titleTextAttributes = [.foregroundColor: UIColor.primaryBlack]

        let buttonAppearance = UIBarButtonItemAppearance()
        buttonAppearance.normal.titleTextAttributes = [.foregroundColor: UIColor.pureBlack]

        appearance.buttonAppearance = buttonAppearance
        
        compactAppearance = appearance
        standardAppearance = appearance
        scrollEdgeAppearance = appearance
    }
}
