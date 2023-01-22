//
//  UITests.swift
//  UITests
//
//  Created by Mohammad Jafarzadeh Rezvan on 1/22/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import XCTest

class UITests: XCTestCase {
    
    override func setUp() {
        continueAfterFailure = false
    }

    func testExample() throws {
        let app = XCUIApplication()
        app.launch()
    }

}
