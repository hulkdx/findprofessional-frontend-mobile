import SwiftUI

struct FilledButton: View {
    
    let text: String
    let action: () -> Void

    var body: some View {
        
        Button(action: action, label: {
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
