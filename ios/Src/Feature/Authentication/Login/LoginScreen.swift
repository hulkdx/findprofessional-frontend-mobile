import SwiftUI
import shared

#if DEBUG
struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
#endif

struct LoginScreen: View {
    @StateObject
    private var viewModel: LoginViewModel = LoginViewModel(KoinHelper().loginUseCase)
    
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var errorMessage: String? = nil
    
    var body: some View {
        VStack(spacing: 0) {
            EmailTextField(value: $email)
            PasswordTextField(value: $password)
                .padding(.top, 16)
            SignInButton() {
                viewModel.signUpButtonClicked()
            }
            .padding(.top, 16)
            SignUpButton() {
                viewModel.signUpButtonClicked()
            }
            .padding(.top, 32)
        }
        .padding(.horizontal, 16)
        .snackbar(message: $errorMessage)
    }
}

private struct SignInButton: View {
    
    let action: () -> Void
    
    var body: some View {
        FilledButton(text: "Sign in", action: action)
    }
}

private struct SignUpButton: View {
    
    let action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            Text("Don’t have an account? **Sign Up**")
                .font(AppFont.body1)
                .padding()
                .foregroundColor(AppColor.Green)
        })
    }
}
