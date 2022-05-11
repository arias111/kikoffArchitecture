//
//  MarketView.swift
//  Kikoff-ios
//
//  Created by r.akhmadeev on 10.05.2022.
//

import Foundation
import UIKit

final class MarketView: UIView {
    typealias DataSource = UICollectionViewDiffableDataSource<Section, AnyHashable>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, AnyHashable>

    enum Section: Int, CaseIterable {
        case categories
        case products
    }

    // MARK: Subviews

    private lazy var collectionView: UICollectionView = {
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: makeCompositionalLayout())
        collectionView.registerCell(MarketCategoryCell.self, MarketProductCell.self)
        collectionView.registerSupplementaryView(
            CollectionHeaderFooterView.self,
            of: UICollectionView.elementKindSectionHeader
        )
        return collectionView
    }()

    private lazy var loader: UIActivityIndicatorView = {
        let view = UIActivityIndicatorView(style: .large)
        view.hidesWhenStopped = true
        return view
    }()

    // MARK: State

    private lazy var dataSource = makeDataSource()

    // MARK: Callbacks

    private var onTapCategory: ((MarketCategory) -> Void)?
    private var onTapProduct: ((MarketProduct) -> Void)?

    // MARK: Init

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Internal Functions

    func observeCategoriesTapping(_ observer: @escaping (MarketCategory) -> Void) {
        onTapCategory = observer
    }

    func observeProductsTapping(_ observer: @escaping (MarketProduct) -> Void) {
        onTapProduct = observer
    }

    func update(categories: [MarketCategory], products: [MarketProduct], animating: Bool = true) {
        var snapshot = Snapshot()
        snapshot.appendSections(Section.allCases)
        snapshot.appendItems(categories, toSection: .categories)
        snapshot.appendItems(products, toSection: .products)
        dataSource.apply(snapshot, animatingDifferences: animating)
    }

    func showLoader() {
        loader.startAnimating()
    }

    func hideLoader() {
        loader.stopAnimating()
    }

    // MARK: Initial Configuration

    private func setupView() {
        addSubview(collectionView) {
            $0.edges.equalToSuperview()
        }

        addSubview(loader) {
            $0.edges.equalToSuperview()
        }

        collectionView.delegate = self
        collectionView.dataSource = dataSource
    }
}

// MARK: - UICollectionViewDataSource

extension MarketView {
    private func makeDataSource() -> DataSource {
        let dataSource = DataSource(collectionView: collectionView) { collectionView, indexPath, item in
            switch item {
            case let category as MarketCategory:
                return collectionView
                    .dequeueCell(MarketCategoryCell.self, for: indexPath)
                    .configure(with: category)
            case let product as MarketProduct:
                return collectionView
                    .dequeueCell(MarketProductCell.self, for: indexPath)
                    .configure(with: product)
            default:
                return nil
            }
        }

        dataSource.supplementaryViewProvider = { collectionView, elementKind, indexPath in
            guard
                elementKind == UICollectionView.elementKindSectionHeader,
                let section = Section(rawValue: indexPath.section)
            else { return nil }

            return collectionView
                .dequeueSupplementaryView(CollectionHeaderFooterView.self, of: elementKind, for: indexPath)
                .configure(with: section.title)
        }

        return dataSource
    }
}

// MARK: - UICollectionViewDelegate

extension MarketView: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let item = dataSource.itemIdentifier(for: indexPath)

        switch item {
        case let item as MarketCategory:
            onTapCategory?(item)
        case let item as MarketProduct:
            onTapProduct?(item)
        default:
            break
        }
    }
}

// MARK: - Compositional Layout

private extension MarketView {
    func makeCompositionalLayout() -> UICollectionViewCompositionalLayout {
        UICollectionViewCompositionalLayout { [unowned self] sectionIndex, _ in
            let sectionType = Section(rawValue: sectionIndex)

            switch sectionType {
            case .categories:
                return makeCategoriesSection()
            case .products:
                return makeProductsSection()
            case .none:
                return nil
            }
        }
    }

    private func makeCategoriesSection() -> NSCollectionLayoutSection {
        let itemSize = NSCollectionLayoutSize(widthDimension: .absolute(80), heightDimension: .estimated(100))
        let item = NSCollectionLayoutItem(layoutSize: itemSize)

        let group = NSCollectionLayoutGroup.horizontal(layoutSize: itemSize, subitems: [item])

        let headerSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1), heightDimension: .estimated(30))
        let header = NSCollectionLayoutBoundarySupplementaryItem(
            layoutSize: headerSize,
            elementKind: UICollectionView.elementKindSectionHeader,
            alignment: .topLeading
        )

        let section = NSCollectionLayoutSection(group: group)
        section.boundarySupplementaryItems = [header]
        section.orthogonalScrollingBehavior = .continuous
        section.interGroupSpacing = 24
        section.contentInsets = NSDirectionalEdgeInsets(top: 8, leading: 16, bottom: 20, trailing: 16)

        return section
    }

    private func makeProductsSection() -> NSCollectionLayoutSection {
        let itemSize = NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1),
            heightDimension: .fractionalHeight(1)
        )
        let item = NSCollectionLayoutItem(layoutSize: itemSize)

        let groupSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1), heightDimension: .absolute(100))
        let group = NSCollectionLayoutGroup.horizontal(layoutSize: groupSize, subitems: [item])

        let headerSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1), heightDimension: .estimated(30))
        let header = NSCollectionLayoutBoundarySupplementaryItem(
            layoutSize: headerSize,
            elementKind: UICollectionView.elementKindSectionHeader,
            alignment: .topLeading
        )

        let section = NSCollectionLayoutSection(group: group)
        section.boundarySupplementaryItems = [header]
        section.contentInsets = NSDirectionalEdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16)
        section.interGroupSpacing = 24

        return section
    }
}

// MARK: - Section + Titles

private extension MarketView.Section {
    var title: String {
        switch self {
        case .categories:
            return "Categories"
        case .products:
            return "Products"
        }
    }
}
