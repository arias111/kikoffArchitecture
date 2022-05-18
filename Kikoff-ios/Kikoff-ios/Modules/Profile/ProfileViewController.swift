//
//  ProfileViewController.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 20.04.2022.
//

import Foundation
import UIKit
import SwiftUI

class ProfileViewController: UIViewController {
	private let customView = ProfileView()
	private let tokenProvider = TokenProvider()
	private let service = ProfileService()
	
	override func viewDidLoad() {
		super.viewDidLoad()
		customView.delegate = self
		service.getBalance { result in
			switch result {
			case .success(let str):
				self.customView.updateUserData(model: str)
			case .failure(let error):
				print(error)
			}
		}
		title = "Nail Galiev"
	}
	
	override func loadView() {
		self.view = customView
	}
}

extension ProfileViewController: ProfileViewDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
	func logout() {
		guard let url = URL(string: "http://localhost:8080/logout") else { return }
		var request = URLRequest(url: url)
		request.httpMethod = "POST"
		request.setValue(tokenProvider.token, forHTTPHeaderField: "X-TOKEN")
		URLSession.shared.dataTask(with: request) { data, _, error in
			if let error = error {
				print(error)
			}
			if let data = data {
				self.tokenProvider.token = nil
				DispatchQueue.main.async {
					self.navigationController?.setViewControllers([AuthorizationViewController()], animated: true)
					print("logout")
				}
			}
		}
		.resume()
	}
	
	func editProfile() {
		let actionSheet = UIAlertController(
			title: nil,
			message: nil,
			preferredStyle: .actionSheet
		)
		
		let camera = UIAlertAction(title: "Camera", style: .default) { _ in
			self.chooseImagePicker(source: .camera)
		}
		
		let photo = UIAlertAction(title: "Photo", style: .default) { _ in
			self.chooseImagePicker(source: .photoLibrary)
		}
				
		let cancel = UIAlertAction(title: "Cancel", style: .cancel)
		
		actionSheet.addAction(camera)
		actionSheet.addAction(photo)
		actionSheet.addAction(cancel)
		present(actionSheet, animated: true)
	}
	
	func chooseImagePicker(source: UIImagePickerController.SourceType) {
		if UIImagePickerController.isSourceTypeAvailable(source) {
			let imagePicker = UIImagePickerController()
			imagePicker.delegate = self
			imagePicker.allowsEditing = true
			imagePicker.sourceType = source
			present(imagePicker, animated: true)
		} else {
			let errorAlert = UIAlertController(title: "Source unavailable", message: nil, preferredStyle: .alert)
			let cancel = UIAlertAction(title: "Ok", style: .cancel)
			errorAlert.addAction(cancel)
			present(errorAlert, animated: true, completion: nil)
		}
	}
	
	func imagePickerController(
		_ picker: UIImagePickerController,
		didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]
	) {
		if let image = info[UIImagePickerController.InfoKey(rawValue: "UIImagePickerControllerEditedImage")] as? UIImage {
			customView.updateImage(with: image)
		}
		picker.dismiss(animated: true, completion: nil)
	}
	
	func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
		picker.dismiss(animated: true, completion: nil)
	}
}
