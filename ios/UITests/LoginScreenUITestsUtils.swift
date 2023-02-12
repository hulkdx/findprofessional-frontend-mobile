import XCTest

func launchLoginScreen(_ app: XCUIApplication) -> LoginBuilder {
    app.launch()
    return LoginBuilder(app: app)
}

struct LoginBuilder {
    let app: XCUIApplication
    
    @discardableResult
    func pressSignUpButton() -> LoginBuilder {
        app.buttons["Don’t have an account? Sign Up"].tap()
        return self
    }
    
    func verify() -> LoginVerifyBuilder {
        return LoginVerifyBuilder(app: app)
    }
}

struct LoginVerifyBuilder {
    let app: XCUIApplication
    
    func signupScreenShown() {
        let element = app.buttons["Sign Up"]
        XCTAssertTrue(element.exists)
    }
}
