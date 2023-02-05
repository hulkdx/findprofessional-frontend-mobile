import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    private let useCase: LoginUseCase

    init(_ useCase: LoginUseCase) {
        self.useCase = useCase
    }
    
    func onCleared() {
    }
    
    func signUpButtonClicked() {
        useCase.onSignUpClicked()
    }
    
    func signInButtonClicked() {
        useCase.onSignInClicked()
    }
}
