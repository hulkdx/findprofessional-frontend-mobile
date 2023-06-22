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
        return SplashScreen()
    }
    
    @ViewBuilder
    func createScreens(screen: NavigationScreen) -> some View {
        if screen is NavigationScreen.Login {
            LoginScreen()
        } else if screen is NavigationScreen.SignUp {
            SignUpScreen()
        } else if screen is NavigationScreen.Home {
            HomeScreen()
        } else if screen is NavigationScreen.Splash {
            SplashScreen()
        } else {
            fatalError("Not implemented")
        }
    }
}

class NavigatorImpl: ObservableObject, Navigator {
    @Published var path: [NavigationScreen] = []
    
    func navigate(screen: NavigationScreen) {
        DispatchQueue.main.async { [weak self] in
            self?.path.append(screen)
        }
    }
}

