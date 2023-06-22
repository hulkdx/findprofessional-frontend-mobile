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
        return createScreens(screen: navigator.firstScreen)
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
    @Published var firstScreen: NavigationScreen = NavigationScreen.Splash()
    @Published var path: [NavigationScreen] = []
    
    func navigate(screen: NavigationScreen) {
        DispatchQueue.main.async { [weak self] in
            self?.navigateMain(screen)
        }
    }

    private func navigateMain(_ screen: NavigationScreen) {
        path.append(screen)
    }

    func navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Bool) {
        DispatchQueue.main.async { [weak self] in
            self?.navigateMain(screen, popTo, inclusive)
        }
    }

    private func navigateMain(_ screen: NavigationScreen, _ popTo: NavigationScreen, _ inclusive: Bool) {
        var found = false
        for i in 0..<path.count {
            let p = path[i]
            if (p == popTo) {
                var newPath: [NavigationScreen] = []
                if (inclusive) {
                    newPath = Array(path[0..<i])

                } else {
                    newPath = Array(path[0...i])
                }
                newPath.append(screen)
                path = newPath
                found = true
                break
            }
        }
        
        if (!found && firstScreen == popTo) {
            if (inclusive) {
                firstScreen = screen
                path = []
            } else {
                path = [screen]
            }
        }
    }
}

