import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        InitKoinKt.doInitKoin { _ in }
    }
    
	var body: some Scene {
		WindowGroup {
			FFNavigation()
		}
	}
}
