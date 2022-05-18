//
//  BasketView.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 17.05.2022.
//

import Foundation
import UIKit
import SnapKit

protocol BasketDelegate: AnyObject {
	func checkOut(address: Address)
}

final class BasketView: UIView {
	private let stackTextView: UIStackView = {
		let stack = UIStackView()
		stack.axis = .vertical
		stack.spacing = 5
		stack.distribution = .fillEqually
		return stack
	}()
	
	private lazy var flatField = UITextField()
	private lazy var houseField = UITextField()
	private lazy var streetField = UITextField()
	
	private lazy var titleLabel: UILabel = {
		let label = UILabel()
		label.text = "Total"
		label.textColor = UIColor(red: 0.235, green: 0.235, blue: 0.263, alpha: 0.6)
		label.textAlignment = .center
		return label
	}()
	
	private lazy var descriptionLabel: UILabel = {
		let label = UILabel()
		label.textAlignment = .center
		return label
	}()
	
	private lazy var stackView: UIStackView = {
		let stack = UIStackView()
		stack.axis = .vertical
		stack.spacing = 10
		return stack
	}()
	
	private lazy var completeButton: UIButton = {
		let button = UIButton(type: .system)
		button.setTitle("Check Out", for: .normal)
		button.setTitleColor(.white, for: .normal)
		button.backgroundColor = #colorLiteral(red: 0.4666666687, green: 0.7647058964, blue: 0.2666666806, alpha: 1)
		button.layer.cornerRadius = 15
		button.addTarget(self, action: #selector(completePressed), for: .touchUpInside)
		return button
	}()
	
	lazy var collectionView: UICollectionView = {
		let collectionView = UICollectionView(frame: .zero, collectionViewLayout: makeCompositionalLayout())
		collectionView.registerCell(MarketProductCell.self)
		return collectionView
	}()
	
	private let view = UIView()
	
	// MARK: - Inits
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		addSubviews()
		setupConstraints()
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	func reloadData() {
		collectionView.reloadData()
		let total = BasketService.shared.products.reduce(0) { partialResult, prod in
			partialResult + prod.priceOfOne
		}
		descriptionLabel.text = "\(total)"
	}
	
	// MARK: - Private Methods
	
	private func addSubviews() {
		addSubview(view)
		addSubview(collectionView)
		addSubview(stackTextView)
		stackTextView.addArrangedSubview(flatField)
		stackTextView.addArrangedSubview(houseField)
		stackTextView.addArrangedSubview(streetField)
		view.addSubview(completeButton)
		view.addSubview(stackView)
		stackView.addArrangedSubview(titleLabel)
		stackView.addArrangedSubview(descriptionLabel)
	}
	
	private func setupConstraints() {
		stackTextView.snp.makeConstraints { make in
			make.left.right.equalToSuperview().inset(10)
			make.height.equalTo(115)
			make.top.equalTo(safeAreaLayoutGuide).offset(10)
		}
		
		collectionView.snp.makeConstraints { make in
			make.left.right.equalToSuperview()
			make.bottom.equalTo(view.snp.top)
			make.top.equalTo(stackTextView.snp.bottom)
		}
		
		view.snp.makeConstraints { make in
			make.left.right.equalToSuperview()
			make.height.equalTo(120)
			make.bottom.equalTo(safeAreaLayoutGuide.snp.bottom).offset(30)
		}
		
		completeButton.snp.makeConstraints { make in
			make.right.equalToSuperview().inset(30)
			make.bottom.equalToSuperview().offset(-45)
			make.height.equalTo(50)
			make.left.equalTo(stackView.snp.right).offset(50)
		}
		
		stackView.snp.makeConstraints { make in
			make.left.equalToSuperview().offset(50)
			make.bottom.equalTo(completeButton.snp.bottom)
		}
	}
	
	private func configure() {
		backgroundColor = .white
		view.layer.cornerRadius = 30
		view.backgroundColor = #colorLiteral(red: 0.9644201306, green: 0.9644201306, blue: 0.9644201306, alpha: 1)
		flatField.placeholder = "Enter your flat"
		streetField.placeholder = "Enter your street"
		houseField.placeholder = "Enter your house number"
	}
	
	weak var delegate: BasketDelegate?
	
	@objc private func completePressed() {
		guard
			let flat = flatField.nonEmptyText.flatMap(Int.init),
			let street = streetField.nonEmptyText,
			let house = houseField.nonEmptyText
		else {
			return
		}
		let address = Address(flat: flat, houseNumber: street, street: house)
		delegate?.checkOut(address: address)
	}
}

// MARK: - Compositional Layout

private extension BasketView {
	func makeCompositionalLayout() -> UICollectionViewCompositionalLayout {
		UICollectionViewCompositionalLayout { [unowned self] _, _ in
			return makeProductsSection()
		}
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
