import SwiftUI
import shared

#if DEBUG
struct MainScreen_Previews: PreviewProvider {
   static var previews: some View {
       MainScreen()
   }
}
#endif

struct MainScreen: View {
    var body: some View {
        Text("MainScreen")
    }
}
