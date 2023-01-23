import SwiftUI
import Combine
import shared

struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignUpScreen()
    }
}

struct SignUpScreen: View {
    @StateObject
    var viewModel = SignUpViewModel(KoinHelper().signUpUseCase)
    
    @State var username: String = ""
    @State var password: String = ""
    
    var body: some View {
        VStack(spacing: 0) {
            EmailTextField(value: username)
            PasswordTextField(value: password)
                .padding(.top, 16)
            SignUpButton() {
                // TODO:
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
