import SwiftUI
import shared

struct FFNavigation_Previews: PreviewProvider {
    static var previews: some View {
        FFNavigation()
    }
}

struct FFNavigation: View {
    
    @StateObject
    var viewModel = NavigationViewModel()
    
    var body: some View {
        NavigationStack(path: $viewModel.path) {
            LoginScreen(
                viewModel: LoginViewModel(KoinHelper().loginUseCase, viewModel: viewModel)
            )
            .navigationDestination(for: String.self, destination: { s in
                SignUpScreen()
            })
        }
    }
}

@MainActor
class NavigationViewModel: ObservableObject {
    @Published var path = NavigationPath()
}
