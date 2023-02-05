import SwiftUI

#if DEBUG
struct Snackbar_Previews: PreviewProvider {
    
    static var previews: some View {
        Snackbar(title: "Error")
    }
    
}
#endif

/*
 * Snackbar for swiftui,
 * Values are from:
 * androidx.compose.material3.Snackbar
 * androidx.compose.material3.tokens.SnackbarTokens
 */
struct Snackbar: View {
    
    let title: String
    
    @State var showBanner: Bool = false
    
    var body: some View {
        if (showBanner) {
            Group {
                Text(title)
                    .font(AppFont.body1)
                    .foregroundColor(AppColor.inverseOnSurface)
                    .padding(.vertical, 6)
                    .frame(maxWidth: 600)
                    .background(AppColor.inverseSurface)
                    .cornerRadius(4)
                    .shadow(radius: 6)
            }
            .padding(.horizontal, 16)
            .transition(AnyTransition.move(edge: .bottom).combined(with: .opacity))
            .animation(.spring(), value: 1)
            .gesture(
                DragGesture()
                    .onChanged { _ in
                        withAnimation {
                            showBanner = false
                        }
                    }
            )
        } else {
            EmptyView()
        }
    }
}
