import SwiftUI
import shared

#if DEBUG
struct HomeScreen_Previews: PreviewProvider {
   static var previews: some View {
       HomeScreen()
   }
}
#endif

struct HomeScreen: View {
    var body: some View {
        Text("HomeScreen")
    }
}
