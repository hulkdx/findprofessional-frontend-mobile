import SwiftUI
import shared

struct EmailTextField: View {
    let value: Binding<String>
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Email",
            hint: MR.strings().email.desc().localized(),
            isSecure: false,
            accessibilityIdentifier: "Email"
        )
    }
}
