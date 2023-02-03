import SwiftUI
import shared

struct AppNavigationView: View {
    
    @StateObject
    private var navigator = NavigatorImpl()
    
    var body: some View {
        NavigationStack(path: $navigator.path) {
            initialScreen()
                .navigationDestination(for: NavigationScreen.self, destination: createScreens)
        }
    }

    func initialScreen() -> some View {
        return loginScreen()
    }
    
    @ViewBuilder
    func createScreens(screen: NavigationScreen) -> some View {
        if screen is NavigationScreen.Login {
            loginScreen()
        } else if screen is NavigationScreen.SignUp {
            signUpScreen()
        } else {
            fatalError("Not implemented")
        }
    }

    func loginScreen() -> some View {
        return LoginScreen(viewModel: LoginViewModel(KoinHelper().loginUseCase, navigator))
    }

    func signUpScreen() -> some View {
        return SignUpScreen(viewModel: SignUpViewModel(KoinHelper().signUpUseCase, navigator))
    }
}

class NavigatorImpl: ObservableObject, Navigator {
    @Published var path = NavigationPath()
    
    func navigate(screen: NavigationScreen) {
        path.append(screen)
    }
}

