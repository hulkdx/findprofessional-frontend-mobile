import XCTest
import shared

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
    
    func testPerformLogin() {
        app.inMemoryApiUser(AuthRequest(email: "test@email.com", password: "some-pass"))
        
        launchLoginScreen(app)
            .typeEmail("test@email.com")
            .typePassword("some-pass")
            .pressSignInButton()
            
            .verify()
            .mainScreenShown()
    }
}
