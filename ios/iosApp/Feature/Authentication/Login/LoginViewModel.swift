import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    let useCase: LoginUseCase
    let viewModel: NavigationViewModel

    init(_ useCase: LoginUseCase,
         viewModel: NavigationViewModel
    ) {
        self.useCase = useCase
        self.viewModel = viewModel
    }
    
    func signInButtonClicked() {
        
    }
    
    func signUpButtonClicked() {
        viewModel.path.append("")
    }
}
