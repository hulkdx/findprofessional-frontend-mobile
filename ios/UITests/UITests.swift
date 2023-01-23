import XCTest

class UITests: XCTestCase {
    
    private var app: XCUIApplication!
    
    override func setUpWithError() throws {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
    }

    func testPerformSignUp() throws {
        pressSignUpButton()
        signupScreenIsShown()
    }
    
    private func pressSignUpButton() {
        app.buttons["Don’t have an account? Sign Up"].tap()
    }

    private func signupScreenIsShown() {
        let element = app.buttons["Sign up"]
        XCTAssertTrue(element.exists)
    }

}
