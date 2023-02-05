import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    
    @Published var email: String = ""
    @Published var password: String = ""
    @Published var error: String? = nil
    
    private let useCase: SignUpUseCase

    private var registerTask: Task<(), Never>? = nil

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
    }

    func onCleared() {
        registerTask?.cancel()
    }

    func onSubmitClicked() {
        registerTask = Task {
            do {
                try await asyncFunction(for: useCase.registerNative(request: RegisterRequest(email: email, password: password)))
            } catch {
                // TODO: convert error to string
                self.error = "Error"
            }
        }
    }
}
