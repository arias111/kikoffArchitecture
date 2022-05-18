//
//  TokenProvider.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 20.04.2022.
//

import Foundation

final class TokenProvider {
    private let defaults = UserDefaults.standard
    
    var token: String? {
        get {
            defaults.string(forKey: .key)
        }
        set {
            defaults.set(newValue, forKey: .key)
			print(newValue)
        }
    }
	
	var basketId: String? {
		get {
			defaults.string(forKey: .basketKey)
		}
		set {
			defaults.set(newValue, forKey: .basketKey)
			print(newValue)
		}
	}
}

private extension String {
    static let key = "AuthTokenKey"
	static let basketKey = "BasketId"
}
