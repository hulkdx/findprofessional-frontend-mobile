import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    private let useCase: LoginUseCase
    
    @Published
    var data = ""
    
    @Published
    var showSignUp = false
    
    init(_ useCase: LoginUseCase) {
        self.useCase = useCase
        
        Task {
            do {
                for try await data in asyncStream(for: useCase.getFlowNative()) { self.data = data }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
    
    func signInButtonClicked() {
        
    }
    
    func signUpButtonClicked() {
        showSignUp = true
    }
}
