import XCTest

class SignUpScreenUITests: XCTestCase {
    
    private let app = XCUIApplication()
    
    override func setUpWithError() throws {
        continueAfterFailure = false
        app.launchArguments = ["in-memory-api"]
    }

    func testPerformSignUp() throws {
        launchSignUpScreen(app)
            .pressSignUpButton()
            .verify()
            .homeScreenShown()
    }
}
