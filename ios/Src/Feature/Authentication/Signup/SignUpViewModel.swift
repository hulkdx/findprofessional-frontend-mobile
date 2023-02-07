import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    
    @Published var email: String = ""
    @Published var password: String = ""
    @Published var error: String? = nil
    
    private let useCase: SignUpUseCase

    private var registerTask: Task<Void, Error>?

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
    }

    func onCleared() {
        registerTask?.cancel()
    }

    func onSubmitClicked() {
        registerTask = Task {
            let error = try await asyncFunction(for: useCase.registerNative(request: RegisterRequest(email: email, password: password)))
            if (error != nil) {
                self.error = error?.localized()
            }
        }
    }
}
