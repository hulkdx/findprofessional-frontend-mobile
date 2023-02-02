import SwiftUI

extension View {
    func navigateTo<Destination: View>(
        isActive: Binding<Bool>,
        destination: Destination
    ) -> some View {
        return NavigationLink(
            destination: destination,
            isActive: isActive,
            label: {
                self
            }
        )
    }
}
