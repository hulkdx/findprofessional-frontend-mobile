import SwiftUI
import shared

struct AppNavigationView: View {
    
    @StateObject
    var navigator = NavigatorImpl()
    
    var body: some View {
        NavigationStack(path: $navigator.path) {
            LoginScreen(viewModel: LoginViewModel(KoinHelper().loginUseCase, navigator))
                .navigationDestination(for: NavigationScreen.self, destination: createScreens)
        }
    }
    
    @ViewBuilder
    func createScreens(screen: NavigationScreen) -> some View {
        if screen is NavigationScreen.Login {
            LoginScreen(viewModel: LoginViewModel(KoinHelper().loginUseCase,navigator))
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

