import SwiftUI
import shared

@MainActor
class SplashViewModel: ObservableObject {
    private let useCase: LoginUseCase
    
    init(_ useCase: LoginUseCase) {
        self.useCase = useCase
    }
    
    func load() async {
        do {
            try await useCase.onSplashScreenLoaded()
        } catch {
            // no-op: usecase already handling exceptions
        }
    }
}
