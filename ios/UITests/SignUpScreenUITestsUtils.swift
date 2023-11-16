import XCTest
import shared

func launchSignUpScreen(_ app: XCUIApplication) -> SignUpBuilder {
    launchLoginScreen(app)
        .pressSignUpButton()
    return SignUpBuilder(app: app)
}

struct SignUpBuilder {
    let app: XCUIApplication
    
    func pressSignUpButton() -> SignUpBuilder {
        app.buttons[MR.strings().signUp.desc().localized()].tap()
        return self
    }
    
    func verify() -> SignUpVerifyBuilder {
        return SignUpVerifyBuilder(app: app)
    }
}

struct SignUpVerifyBuilder {
    let app: XCUIApplication
    
    func homeScreenShown() {
        let element = app.staticTexts["HomeScreen"]
        XCTAssertTrue(element.exists)
    }
}
