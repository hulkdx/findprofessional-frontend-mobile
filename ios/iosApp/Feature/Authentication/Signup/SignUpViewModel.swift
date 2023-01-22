import SwiftUI
import Combine
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    private let useCase: SignUpUseCase

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
        
        Task {
            do {
                for try await data in asyncStream(for: useCase.greetingNative()) { print(data) }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}
