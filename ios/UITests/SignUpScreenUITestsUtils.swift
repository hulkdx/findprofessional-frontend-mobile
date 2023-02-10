import XCTest

func launchSignUpScreen(_ app: XCUIApplication) -> SignUpBuilder {
    launchLoginScreen(app)
        .pressSignUpButton()
    return SignUpBuilder(app: app)
}

struct SignUpBuilder {
    let app: XCUIApplication
    
    func pressSignUpButton() -> SignUpBuilder {
        app.buttons["Sign Up"].tap()
        return self
    }
    
    func verify() -> SignUpVerifyBuilder {
        return SignUpVerifyBuilder(app: app)
    }
}

struct SignUpVerifyBuilder {
    let app: XCUIApplication
    
    func mainScreenShown() {
        let element = app.staticTexts["MainScreen"]
        XCTAssertTrue(element.exists)
    }
}
