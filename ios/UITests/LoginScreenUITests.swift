import XCTest

class LoginScreenUITests: XCTestCase {
    
    private let app: XCUIApplication = XCUIApplication()
    
    override func setUpWithError() throws {
        continueAfterFailure = false
        
        app.launchArguments = ["in-memory-api"]
    }

    func testPerformSignUp() throws {
        launchLoginScreen(app)
            .pressSignUpButton()
        
            .verify()
            .signupScreenShown()
    }
    
    func testPerformLogin() {
        launchLoginScreen(app)
            .typeEmail("test@email.com")
            .typePassword("test@email.com")
            .pressSignInButton()
            
            .verify()
            .mainScreenShown()
    }
}
