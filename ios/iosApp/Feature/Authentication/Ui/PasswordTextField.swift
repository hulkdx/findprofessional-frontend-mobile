import SwiftUI

struct PasswordTextField: View {
    let value: Binding<String>
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Password",
            hint: "Password",
            isSecure: true
        )
    }
}
