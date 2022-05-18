//
//  MarketPresenter.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import Foundation
import Combine

enum MarketState {
    case loaded(categories: [MarketCategory], products: [MarketProduct])
    case failed(Error)
}

struct MarketCategory: Hashable, Decodable {
    let id: Int
    let name: String
    let imageUrl: URL?
}

struct MarketProduct: Hashable, Codable {
    let id: Int
    let name: String
    let categoryId: Int
    let countOfProducts: Int
    let priceOfOne: Int
    let imageUrl: URL?
}

protocol IMarketProvider {
    var statePublisher: AnyPublisher<MarketState, Never> { get }
    var loadingPublisher: AnyPublisher<Bool, Never> { get }
    func didLoad()
    func didTap(category: MarketCategory)
}

final class MarketProvider: IMarketProvider {
    let stateSubject: PassthroughSubject<MarketState, Never> = PassthroughSubject()
    let loadingSubject: PassthroughSubject<Bool, Never> = PassthroughSubject()

    private let api = MarketApi()
    private var loadedCategories: [MarketCategory] = []
    private var productsMap: [MarketCategory: [MarketProduct]] = [:]
    private var cancellable: AnyCancellable?

    var statePublisher: AnyPublisher<MarketState, Never> {
        stateSubject.eraseToAnyPublisher()
    }

    var loadingPublisher: AnyPublisher<Bool, Never> {
        loadingSubject.eraseToAnyPublisher()
    }

    func didLoad() {
        loadingSubject.send(true)

        cancellable = api
            .loadAllCategories()
            .flatMap { [api] in api.loadProducts(for: $0) }
            .sink(receiveCompletion: { [weak self] completion in
                self?.handle(completion: completion)
            }, receiveValue: { [weak self] dto in
                guard let self = self else { return }
                self.loadedCategories = dto.allCategories
                self.productsMap[dto.firstCategory] = dto.productsForFirstCategory
                self.stateSubject.send(.loaded(categories: dto.allCategories, products: dto.productsForFirstCategory))
            })
    }

    func didTap(category: MarketCategory) {
        if let products = productsMap[category] {
            stateSubject.send(.loaded(categories: loadedCategories, products: products))
            return
        }

        cancellable?.cancel()
        loadingSubject.send(true)

        cancellable = api.loadProducts(withCategoryId: category.id)
            .sink(receiveCompletion: { [weak self] completion in
                self?.handle(completion: completion)
            }, receiveValue: { [weak self] products in
                guard let self = self else { return }
                self.productsMap[category] = products
                self.stateSubject.send(.loaded(categories: self.loadedCategories, products: products))
            })
    }

    private func handle(completion: Subscribers.Completion<Error>) {
        print(completion)
        loadingSubject.send(false)

        if case let .failure(error) = completion {
            stateSubject.send(.failed(error))
        }
    }
}

private struct InitialLoadingDTO {
    let allCategories: [MarketCategory]
    let firstCategory: MarketCategory
    let productsForFirstCategory: [MarketProduct]
}

private struct LoadingError: Error {}

private extension MarketApi {
    func loadProducts(for categories: [MarketCategory]) -> AnyPublisher<InitialLoadingDTO, Error> {
        guard let first = categories.first else {
            return Fail(outputType: InitialLoadingDTO.self, failure: LoadingError())
                .eraseToAnyPublisher()
        }

        return loadProducts(withCategoryId: first.id)
            .map {
                InitialLoadingDTO(
                    allCategories: categories,
                    firstCategory: first,
                    productsForFirstCategory: $0)
            }
            .eraseToAnyPublisher()
    }
}
