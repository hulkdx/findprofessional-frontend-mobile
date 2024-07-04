import SwiftUI
import shared

@main
struct MainApp: App {
    init() {
        InitKoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
