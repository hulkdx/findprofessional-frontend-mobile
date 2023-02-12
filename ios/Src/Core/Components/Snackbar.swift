import SwiftUI

#if DEBUG
struct Snackbar_Previews: PreviewProvider {
    @State static var state: String? = "Error"
    static var previews: some View {
        Snackbar(message: $state)
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
    
    @Binding var message: String?
    
    var body: some View {
        if (message != nil) {
            Group {
                Text(message ?? "")
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
                            message = nil
                        }
                    }
            )
        } else {
            EmptyView()
        }
    }
}

public extension View {
    func snackbar(message: Binding<String?>) -> some View {
        ZStack(alignment: .bottom) {
            self
                .frame(maxHeight: .infinity)
            
            Snackbar(message: message)
        }
    }
}

