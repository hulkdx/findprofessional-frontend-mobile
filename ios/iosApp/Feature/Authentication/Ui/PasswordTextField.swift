import SwiftUI

struct PasswordTextField: View {
    @State
    var value: String
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Password",
            hint: "Password",
            isSecure: true
        )
    }
}
