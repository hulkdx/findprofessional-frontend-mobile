import SwiftUI
import shared

@main
struct MainApp: App {
    
    init() {
        InitKoinKt.doInitKoin { koinApp in
            koinApp.addNavigatorAsSingle { _, _ in NavigatorImpl() }
        }

        // ui tests
#if DEBUG
        if CommandLine.arguments.contains("in-memory-api") {
            InMemoryApi.shared.loadKoinModules()
        }
#endif
    }
    
	var body: some Scene {
		WindowGroup {
            AppNavigationView()
		}
	}
}
