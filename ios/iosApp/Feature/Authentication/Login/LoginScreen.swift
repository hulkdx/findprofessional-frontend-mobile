import SwiftUI
import Combine
import shared

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}

struct LoginScreen: View {
    @StateObject
    var viewModel = LoginViewModel(repository: KoinHelper().loginRepository)
    
    @State var username: String = ""
    @State var password: String = ""
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                EmailTextField(value: username)
                PasswordTextField(value: password)
                    .padding(.top, 16)
                SignInButton() {
                    viewModel.signUpButtonClicked()
                }
                    .padding(.top, 16)
                SignUpButton() {
                    viewModel.signUpButtonClicked()
                }
                    .padding(.top, 32)
                    .navigateTo(isActive: $viewModel.showSignUp, destination: SignUpScreen())
            }
            .padding(.horizontal, 16)
        }
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
