import SwiftUI

struct EmailTextField: View {
    let value: Binding<String>
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Email",
            hint: "Email",
            isSecure: false
        )
    }
}
