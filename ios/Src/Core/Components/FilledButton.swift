import SwiftUI

struct FilledButton: View {
    
    let text: String
    let action: () async -> Void
    
    var body: some View {
        AsyncButton(action: action, label: {
            Text(text)
                .frame(maxWidth: .infinity)
                .font(AppFont.h3)
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(AppColor.Green)
                )
                .foregroundColor(Color.white)
        })
    }
}
