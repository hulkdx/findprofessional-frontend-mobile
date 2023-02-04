import SwiftUI
import shared

struct AppNavigationView: View {
    
    @StateObject
    private var navigator: NavigatorImpl = KoinHelper().navigator as! NavigatorImpl
    
    var body: some View {
        NavigationStack(path: $navigator.path) {
            initialScreen()
                .navigationDestination(for: NavigationScreen.self, destination: createScreens)
        }
    }

    func initialScreen() -> some View {
        return LoginScreen()
    }
    
    @ViewBuilder
    func createScreens(screen: NavigationScreen) -> some View {
        if screen is NavigationScreen.Login {
            LoginScreen()
        } else if screen is NavigationScreen.SignUp {
            SignUpScreen()
        } else {
            fatalError("Not implemented")
        }
    }
}

class NavigatorImpl: ObservableObject, Navigator {
    @Published var path = NavigationPath()
    
    func navigate(screen: NavigationScreen) {
        path.append(screen)
    }
}

