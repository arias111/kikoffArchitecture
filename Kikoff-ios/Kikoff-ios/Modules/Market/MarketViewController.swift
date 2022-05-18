//
//  MarketViewController.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import UIKit
import Combine

protocol IMarketViewController: AnyObject {
    func show(state: MarketState)
    func show(error: Error)
}

final class MarketViewController: UIViewController, RootViewContainable {
    typealias RootView = MarketView

    private let provider: IMarketProvider
    private var disposeBag: Set<AnyCancellable> = []

    // MARK: Init

    init(provider: IMarketProvider = MarketProvider()) {
        self.provider = provider
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Life Cycle

    override func loadView() {
        view = MarketView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        provider.didLoad()
    }

    // MARK: Initial Configuration

    private func setupView() {
        title = "Kikoff Market"
        navigationItem.largeTitleDisplayMode = .always
        rootView.observeProductsTapping { [unowned self] in show(product: $0) }
        rootView.observeCategoriesTapping { [unowned self] in provider.didTap(category: $0) }

        provider.loadingPublisher
            .sink { [rootView] in $0 ? rootView.showLoader() : rootView.hideLoader() }
            .store(in: &disposeBag)

        provider.statePublisher
            .sink { [weak self] state in
                switch state {
                case let .loaded(categories, products):
                    self?.rootView.update(categories: categories, products: products)
                case let .failed(error):
                    self?.showAlert(error: error)
                }
            }
            .store(in: &disposeBag)
    }

    private func showAlert(error: Error) {
        let alert = UIAlertController(
            title: "Failure",
            message: error.localizedDescription,
            preferredStyle: .alert
        )

        alert.addAction(.init(title: "ok", style: .cancel))
        present(alert, animated: true)
    }

    private func show(product: MarketProduct) {

    }
}
