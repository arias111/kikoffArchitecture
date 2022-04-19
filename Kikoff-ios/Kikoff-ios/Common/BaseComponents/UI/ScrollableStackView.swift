//
//  ScrollableStackView.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 18.04.2022.
//

import Foundation
import UIKit

final class ScrollableStackView: UIView {
    struct Style {
        let contentInsets: UIEdgeInsets
    }
    
    private let style: Style
    
    // MARK: Subviews
    
    private lazy var scrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.alwaysBounceVertical = true
        scrollView.keyboardDismissMode = .onDrag
        return scrollView
    }()
    
    private lazy var containerView = UIView()

    private lazy var stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        return stackView
    }()
    
    // MARK: Init
    
    init(style: Style = .default) {
        self.style = style
        super.init(frame: .zero)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Controls
    
    func set(_ views: UIView...) {
        views.forEach(stackView.addArrangedSubview(_:))
    }
    
    // MARK: Helpers
    
    private func setupView() {
        addSubview(scrollView) {
            $0.edges.equalToSuperview()
        }
        
        scrollView.addSubview(containerView) {
            $0.edges.width.equalToSuperview()
        }
        
        containerView.addSubview(stackView) {
            $0.edges.equalToSuperview().inset(style.contentInsets)
        }
    }
}

// MARK: - Style + Default

extension ScrollableStackView.Style {
    static var `default`: ScrollableStackView.Style {
        ScrollableStackView.Style(
            contentInsets: .init(top: 16, left: 16, bottom: 0, right: 16)
        )
    }
}
