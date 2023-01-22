import SwiftUI
import Combine
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    private let repository: SignUpRepository

    init(repository: SignUpRepository) {
        self.repository = repository
        
        Task {
            do {
                for try await data in asyncStream(for: repository.greetingNative()) { print(data) }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}
