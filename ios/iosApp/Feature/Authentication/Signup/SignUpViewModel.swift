import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    
    @Published var email: String = ""
    @Published var password: String = ""
    
    private let useCase: SignUpUseCase

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
    }

    func onSubmitClicked() {
        let task = Task {
            do {
                try await asyncFunction(for: useCase.registerNative(request: RegisterRequest(email: email, password: password)))
                print("onSubmitClicked: Success")
            } catch {
                print("onSubmitClicked: Failed with error: \(error)")
            }
        }
    }
}
