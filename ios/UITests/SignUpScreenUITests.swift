import XCTest

class SignUpScreenUITests: XCTestCase {
    
    private let app = XCUIApplication()
    
    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    func testPerformSignUp() throws {
        launchSignUpScreen(app)
            .pressSignUpButton()
            .verify()
            .mainScreenShown()
    }
}
