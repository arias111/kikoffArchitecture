//
//  TappableButton.swift
//  Kikoff-ios
//
//  Created by Руслан Ахмадеев on 19.04.2022.
//

import UIKit

class TappableButton: UIButton {
    private var onTap: VoidClosure?
    
    convenience init(onTap: @escaping VoidClosure) {
        self.init(frame: .zero)
        enableTapping(onTap)
    }
    
    func enableTapping(_ onTap: @escaping VoidClosure) {
        self.onTap = onTap
        addTarget(self, action: #selector(tapped), for: .touchUpInside)
    }
    
    @objc private func tapped() {
        onTap?()
    }
}
