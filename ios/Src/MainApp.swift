import SwiftUI
import shared

@main
struct MainApp: App {
    init() {
        KoinFactoryIos().doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
