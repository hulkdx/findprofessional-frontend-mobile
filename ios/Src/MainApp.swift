import SwiftUI
import shared

@main
struct MainApp: App {
    
    init() {
        InitKoinKt.doInitKoin { koinApp in
            koinApp.addNavigatorAsSingle { _, _ in NavigatorImpl() }
        }

#if DEBUG
        handleUiTestArguments()
#endif
    }
    
	var body: some Scene {
		WindowGroup {
            AppNavigationView()
		}
	}
}
