import XCTest

func launchLoginScreen(_ app: XCUIApplication) -> LoginBuilder {
    app.launch()
    return LoginBuilder(app: app)
}

struct LoginBuilder {
    let app: XCUIApplication
    
    func typeEmail(_ email: String) -> LoginBuilder {
        let element = app.textFields["Email"]
        element.tap()
        element.typeText(email)
        return self
    }

    func typePassword(_ password: String) -> LoginBuilder {
        let element = app.secureTextFields["Password"]
        element.tap()
        element.typeText(password)
        return self
    }
    
    @discardableResult
    func pressSignUpButton() -> LoginBuilder {
        app.buttons["Don’t have an account? Sign Up"].tap()
        return self
    }
    
    func pressSignInButton() -> LoginBuilder {
        app.buttons["Sign in"].tap()
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
    
    func homeScreenShown() {
        let element = app.staticTexts["HomeScreen"]
        XCTAssertTrue(element.exists)
    }
}
