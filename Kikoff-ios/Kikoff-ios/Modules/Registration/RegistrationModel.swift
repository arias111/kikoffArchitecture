//
//  RegistrationModel.swift
//  Kikoff-ios
//
//  Created by Nail Galiev on 27.04.2022.
//

import Foundation

struct RegistrationModel: Encodable {
	let birthday: String
	let creationDate: String
	let email: String
	let firstName: String
	let lastName: String
	let password: String
	let patronymic: String
}
