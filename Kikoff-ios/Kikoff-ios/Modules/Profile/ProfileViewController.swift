//
//  ProfileViewController.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 20.04.2022.
//

import Foundation
import UIKit

class ProfileViewController: UIViewController {
	private let customView = ProfileView()
	
	override func viewDidLoad() {
		super.viewDidLoad()
		customView.delegate = self
		title = "Nail Galiev"
	}
	
	override func loadView() {
		self.view = customView
	}
}

extension ProfileViewController: ProfileViewDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
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
