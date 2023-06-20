import SwiftUI
import shared

#if DEBUG
struct SplashScreen_Previews: PreviewProvider {
    static var previews: some View {
        SplashScreen()
    }
}
#endif


struct SplashScreen: View {
    @StateObject
    private var viewModel: SplashViewModel = SplashViewModel(KoinHelper().loginUseCase)
    
    var body: some View {
        ZStack {
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: AppColor.Green))
        }
    }
}
