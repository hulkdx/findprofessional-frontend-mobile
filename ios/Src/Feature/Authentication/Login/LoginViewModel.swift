import SwiftUI
import shared

@MainActor
class LoginViewModel: ObservableObject {
    
    @Published var email: String = ""
    @Published var password: String = ""
    @Published var error: String? = nil
    
    private let useCase: LoginUseCase

    init(_ useCase: LoginUseCase) {
        self.useCase = useCase
    }
    
    func onDisappear() {
    }
    
    func signUpButtonClicked() {
        useCase.onSignUpClicked()
    }
    
    func signInButtonClicked() async {
        do {
            let error = try await useCase.onSignInClicked(request: LoginRequest(email: email, password: password))
            if (error != nil) {
                self.error = error?.localized()
            }
        } catch {
            // no-op: usecase already handling exceptions
        }
    }
}
