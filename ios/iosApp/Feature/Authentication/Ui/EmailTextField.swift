import SwiftUI

struct EmailTextField: View {
    @State
    var value: String
    
    var body: some View {
        CommonTextField(
            value: value,
            leadingIcon: "Email",
            hint: "Email",
            isSecure: false
        )
    }
}
