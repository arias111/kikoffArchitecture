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
        }
    }
}

private extension String {
    static let key = "AuthTokenKey"
}
