import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        InitKoinKt.doInitKoin { koinApp in
            koinApp.addNavigatorAsSingle { _, _ in NavigatorImpl() }
        }
    }
    
	var body: some Scene {
		WindowGroup {
            AppNavigationView()
		}
	}
}
