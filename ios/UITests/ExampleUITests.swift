import XCTest
import shared

class ExampleUITests: XCTestCase {
    
    private let app: XCUIApplication = XCUIApplication()
    
    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    func testExample() throws {
        XCTAssertTrue(true)
    }

}
