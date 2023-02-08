import XCTest

class LoginScreenUITests: XCTestCase {
    
    private let app: XCUIApplication = XCUIApplication()
    
    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    func testPerformSignUp() throws {
        launchLoginScreen(app)
            .pressSignUpButton()
        
            .verify()
            .signupScreenShown()
    }
}
