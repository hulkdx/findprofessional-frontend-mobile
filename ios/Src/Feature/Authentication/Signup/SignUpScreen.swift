import SwiftUI

struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        AppNavigationView().signUpScreen()
    }
}

struct SignUpScreen: View {
    @StateObject
    var viewModel: SignUpViewModel
    
    var body: some View {
        VStack(spacing: 0) {
            EmailTextField(value: $viewModel.email)
            PasswordTextField(value: $viewModel.password)
                .padding(.top, 16)
            SignUpButton() {
                viewModel.onSubmitClicked()
            }
                .padding(.top, 16)
        }
        .padding(.horizontal, 16)
    }
}

private struct SignUpButton: View {
    
    let action: () -> Void

    var body: some View {
        FilledButton(text: "Sign up", action: action)
    }
}
