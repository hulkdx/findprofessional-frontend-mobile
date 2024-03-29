import Foundation
import shared

#if DEBUG
func handleUiTestArguments() {
    KoinHelper().clearDatastore()
    
    ProcessInfo.processInfo.arguments.forEach { arg in
        if arg == ArgKt.IN_MEMORY_API {
            InMemoryApi.shared.loadKoinModules()
        }
        if arg.starts(with: ArgKt.IN_MEMORY_API_USER) {
            let authRequestStr = arg.replacingOccurrences(of: ArgKt.IN_MEMORY_API_USER, with: "")
            let authRequest = SerializableHelper().decodeAuthRequest(value: authRequestStr)
            
            InMemoryApi.shared.setUser(authRequest)
        }
    }
}

#endif
