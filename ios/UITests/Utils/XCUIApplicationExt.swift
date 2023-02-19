import XCTest
import shared

extension XCUIApplication {
    func inMemoryApi() {
        launchArguments.append(ArgKt.IN_MEMORY_API)
    }
    
    func inMemoryApiUser(_ a: AuthRequest) {
        launchArguments.append(ArgKt.IN_MEMORY_API)
        launchArguments.append("\(ArgKt.IN_MEMORY_API_USER)\(SerializableHelper().encodeAuthRequest(value: a))")
    }
}
