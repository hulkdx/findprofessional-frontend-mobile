import XCTest
import shared

extension XCUIApplication {
    func inMemoryApi() {
        launchArguments.append(ArgKt.IN_MEMORY_API)
    }
    
    func inMemoryApiUser(email: String, password: String) {
        inMemoryApiUser(RegisterRequest(email: email, password: password, firstName: "", lastName: ""))
    }
    
    func inMemoryApiUser(_ a: RegisterRequest) {
        launchArguments.append(ArgKt.IN_MEMORY_API)
        launchArguments.append("\(ArgKt.IN_MEMORY_API_USER)\(SerializableHelper().encodeAuthRequest(value: a))")
    }
}
