import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    private let useCase: LoginUseCase
    private let navigator: Navigator

    init(_ useCase: LoginUseCase, _ navigator: Navigator) {
        self.useCase = useCase
        self.navigator = navigator
    }
    
    func signInButtonClicked() {
        
    }
    
    func signUpButtonClicked() {
        navigator.navigate(screen: NavigationScreen.SignUp())
    }
}
