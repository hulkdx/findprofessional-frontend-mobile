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
    
    var body: some View {
        VStack(spacing: 0) {
            EmailTextField(value: $viewModel.email)
            PasswordTextField(value: $viewModel.password)
                .padding(.top, 16)
            SignInButton() {
                await viewModel.signInButtonClicked()
            }
            .padding(.top, 16)
            SignUpButton() {
                viewModel.signUpButtonClicked()
            }
            .padding(.top, 32)
        }
        .padding(.horizontal, 16)
        .onDisappear { viewModel.onDisappear() }
        .snackbar(message: $viewModel.error)
    }
}

private struct SignInButton: View {
    
    let action: () async -> Void
    
    var body: some View {
        FilledButton(text: Res.string().signIn.localized(), action: action)
    }
}

private struct SignUpButton: View {
    
    let action: () -> Void
    
    var body: some View {
        Button(action: action, label: {
            Text("\(Res.string().dontHaveAnAccount.localized()) **\(Res.string().signUp.localized())**")
                .font(AppFont.body1)
                .padding()
                .foregroundColor(AppColor.Green)
        })
    }
}
