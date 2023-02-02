import SwiftUI

struct FFNavigation_Previews: PreviewProvider {
    static var previews: some View {
        FFNavigation()
    }
}

struct FFNavigation: View {
    
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            LoginScreen()
        }
    }
}
