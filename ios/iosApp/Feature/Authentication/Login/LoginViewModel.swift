import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    let useCase: LoginUseCase
    let viewModel: NavigationViewModel

    @Published
    var data = ""
    
//    var path = NavigationPath()
    
    init(_ useCase: LoginUseCase,
         viewModel: NavigationViewModel
//         , @Binding path: NavigationPath
    ) {
        self.useCase = useCase
        self.viewModel = viewModel
//        self.path = path
    }
    
    func signInButtonClicked() {
        
    }
    
    func signUpButtonClicked() {
        viewModel.path.append("")
    }
}
