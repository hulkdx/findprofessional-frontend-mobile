//
//  UITests.swift
//  UITests
//
//  Created by Mohammad Jafarzadeh Rezvan on 1/22/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import XCTest

class UITests: XCTestCase {
    
    private var app: XCUIApplication!
    
    override func setUpWithError() throws {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
    }

    func testExample() throws {
        //app.descendants(matching: .any)[""]
    }

}
