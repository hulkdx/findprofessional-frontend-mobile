import SwiftUI

struct CommonTextField: View {
    
    @Binding var value: String
    let leadingIcon: String
    let hint: String
    let isSecure: Bool
    let accessibilityIdentifier: String
    
    @FocusState private var focused: Bool
    
    var body: some View {
        HStack {
            Image(leadingIcon)
            if isSecure {
                createSecure()
            } else {
                createNormal()
            }
        }
        .padding()
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(lineWidth: 1)
                .foregroundColor(focused ? AppColor.LightGreenBorder : AppColor.LightGreyBorder)
        )
        .background(AppColor.LightGrey)
    }
    
    private func createSecure() -> some View {
        return SecureField("", text: $value)
            .focused($focused)
            .placeholder(when: value.isEmpty) {
                Text(hint)
                    .font(AppFont.body2)
                    .foregroundColor(AppColor.TextColor)
            }
            .font(AppFont.body2)
            .accessibilityIdentifier(accessibilityIdentifier)
    }
    
    private func createNormal() -> some View {
        return TextField("", text: $value)
            .focused($focused)
            .placeholder(when: value.isEmpty) {
                Text(hint)
                    .font(AppFont.body2)
                    .foregroundColor(AppColor.TextColor)
            }
            .font(AppFont.body2)
            .accessibilityIdentifier(accessibilityIdentifier)
    }
}

private extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {
            
            ZStack(alignment: alignment) {
                placeholder().opacity(shouldShow ? 1 : 0)
                self
            }
        }
}

