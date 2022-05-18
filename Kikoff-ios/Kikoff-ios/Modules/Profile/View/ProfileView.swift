//
//  ProfileView.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 20.04.2022.
//

import Foundation
import UIKit
import SnapKit

extension ProfileView {
	private struct Appearance {
		let titleFont = UIFont.systemFont(ofSize: 24, weight: .bold)
		let descriptionFont = UIFont.systemFont(ofSize: 20)
		let imageSize: CGFloat = UIScreen.main.bounds.width > 375 ? 240 : 200
		let topImageOffset: CGFloat = 7
		let buttonSize: CGFloat = 40
		let topOffset: CGFloat = 32
		let descriptionTopOffset: CGFloat = 32
		let sideOffset: CGFloat = 78
		let saveButtonLeftOffset: CGFloat = 40
		let saveButtonHeight: CGFloat = 40
		let saveButtonBottomOffset: CGFloat = -30
		let editButtonTop: CGFloat = -18
		let editButtonRight: CGFloat = 12
		let cornerRadius: CGFloat = 14
		let buttonColor = UIColor(red: 0.965, green: 0.965, blue: 0.965, alpha: 1)
		let numberOfLines: Int = 0
		
		let imageTitle: String = "profile"
		let titleLabel: String = "Your Balance"
		let descriptionLabel: String = "0 $"
		let saveButtonTitle: String = "Pay"
		let editButtonTitle: String = "Edit"
	}
}

protocol ProfileViewDelegate: AnyObject {
	func editProfile()
	func logout()
}

final class ProfileView: UIView {
	weak var delegate: ProfileViewDelegate?
	private let appearance = Appearance()

	private lazy var avatarImage: UIImageView = {
		let imageView = UIImageView()
		imageView.image = UIImage(named: appearance.imageTitle)
		imageView.clipsToBounds = true
		return imageView
	}()
	
	private lazy var titleLabel: UILabel = {
		let label = UILabel()
		label.text = appearance.titleLabel
		label.textAlignment = .center
		label.font = appearance.titleFont
		return label
	}()
	
	private lazy var descriptionLabel: UILabel = {
		let label = UILabel()
		label.font = appearance.descriptionFont
		label.text = appearance.descriptionLabel
		label.numberOfLines = appearance.numberOfLines
		label.textAlignment = .center
		return label
	}()
	
	private lazy var saveButton: UIButton = {
		let button = UIButton(type: .system)
		button.setTitle(appearance.saveButtonTitle, for: .normal)
		button.setTitleColor(.systemBlue, for: .normal)
		button.layer.cornerRadius = appearance.cornerRadius
		button.backgroundColor = appearance.buttonColor
		return button
	}()
	
	private lazy var editButton: UIButton = {
		let button = UIButton(type: .system)
		button.setTitle(appearance.editButtonTitle, for: .normal)
		button.setTitleColor(.systemBlue, for: .normal)
		button.addTarget(self, action: #selector(editPressed), for: .touchUpInside)
		return button
	}()
	
	private lazy var logoutButton: UIButton = {
		let button = UIButton(type: .system)
		button.setTitle("Logout", for: .normal)
		button.setTitleColor(.systemBlue, for: .normal)
		button.layer.cornerRadius = appearance.cornerRadius
		button.backgroundColor = appearance.buttonColor
		button.addTarget(self, action: #selector(logout), for: .touchUpInside)
		return button
	}()
	
	// MARK: - Inits
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		addSubviews()
		setupConstraints()
		configure()
		loadImage()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	// MARK: - Private Methods
	
	private func addSubviews() {
		addSubview(titleLabel)
		addSubview(descriptionLabel)
		addSubview(avatarImage)
		addSubview(saveButton)
		addSubview(editButton)
		addSubview(logoutButton)
	}
	
	private func setupConstraints() {
		avatarImage.snp.makeConstraints { make in
			make.height.width.equalTo(appearance.imageSize)
			make.centerX.equalToSuperview()
			make.top.equalTo(safeAreaLayoutGuide).inset(70)
		}
		
		titleLabel.snp.makeConstraints { make in
			make.top.equalTo(avatarImage.snp.bottom).offset(appearance.topOffset)
			make.centerX.equalToSuperview()
		}
		
		descriptionLabel.snp.makeConstraints { make in
			make.top.equalTo(titleLabel.snp.bottom).offset(20)
			make.left.right.equalToSuperview().inset(appearance.sideOffset)
		}

		saveButton.snp.makeConstraints { make in
			make.left.right.equalToSuperview().inset(appearance.saveButtonLeftOffset)
			make.height.equalTo(appearance.saveButtonHeight)
			make.bottom.equalTo(safeAreaLayoutGuide.snp.bottom).offset(appearance.saveButtonBottomOffset)
		}
		
		editButton.snp.makeConstraints { make in
			make.width.height.equalTo(appearance.buttonSize)
			make.top.equalTo(avatarImage.snp.bottom).offset(appearance.editButtonTop)
			make.right.equalTo(avatarImage.snp.right).offset(appearance.editButtonRight)
		}
		
		logoutButton.snp.makeConstraints { make in
			make.right.equalToSuperview().offset(-30)
			make.top.equalToSuperview().offset(30)
			make.height.equalTo(appearance.saveButtonHeight)
			make.width.equalTo(appearance.saveButtonHeight * 2)
		}
	}
	
	private func configure() {
		backgroundColor = .white
		avatarImage.layer.cornerRadius = appearance.imageSize / 2
	}
	
	private func saveImage(image: UIImage) {
		guard let data = image.jpegData(compressionQuality: 0.5) else { return }
		let encoded = try? PropertyListEncoder().encode(data)
		UserDefaults.standard.set(encoded, forKey: "image")
	}

	private func loadImage() {
		guard let data = UserDefaults.standard.data(forKey: "image")
		else { return }
		if let decoded = (try? PropertyListDecoder().decode(Data.self, from: data)) {
			let image = UIImage(data: decoded)
			avatarImage.image = image
		}
	}
	
	func updateUserData(model: String) {
		DispatchQueue.main.async {
			self.descriptionLabel.text = "₽ \(model)"
		}
	}
	
	func updateImage(with image: UIImage) {
		saveImage(image: image)
		loadImage()
	}
		
	@objc private func editPressed() {
		delegate?.editProfile()
	}
	
	@objc private func logout() {
		delegate?.logout()
	}
}
