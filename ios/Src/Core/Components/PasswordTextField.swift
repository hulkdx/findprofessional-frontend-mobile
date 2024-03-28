import SwiftUI
import shared

struct PasswordTextField: View {
    let value: Binding<String>
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Password",
            hint: Res.string().password.localized(),
            isSecure: true,
            accessibilityIdentifier: "Password"
        )
    }
}
