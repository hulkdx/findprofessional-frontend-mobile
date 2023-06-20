import SwiftUI
import shared

@MainActor
class SplashViewModel: ObservableObject {
    private let useCase: LoginUseCase
    
    init(_ useCase: LoginUseCase) {
        self.useCase = useCase
        
        Task {
            do {
                try await useCase.onSplashScreenLoaded()
            } catch {
                // no-op: usecase already handling exceptions
            }
        }
    }
}
