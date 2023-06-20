import Foundation
import shared

#if DEBUG
func handleUiTestArguments() {
    clearUiTestCache()
    ProcessInfo.processInfo.arguments.forEach { arg in
        if arg == ArgKt.IN_MEMORY_API {
            InMemoryApi.shared.loadKoinModules()
        }
        if arg.starts(with: ArgKt.IN_MEMORY_API_USER) {
            let authRequestStr = arg.replacingOccurrences(of: ArgKt.IN_MEMORY_API_USER, with: "")
            let authRequest = SerializableHelper().decodeAuthRequest(value: authRequestStr)
            
            InMemoryApi.shared.user = authRequest
        }
    }
}

private func clearUiTestCache() {
    KoinHelper().accessTokenStorage.remove { e in }
}
#endif
