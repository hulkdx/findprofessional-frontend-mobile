import SwiftUI
import shared

@main
struct MainApp: App {
    
    init() {
        InitKoinIOSKt.doInitKoinIOS(
            navigator:  { _, _ in NavigatorImpl() }
        )

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
