import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    
    @Published var email: String = ""
    @Published var password: String = ""
    @Published var error: String? = nil
    
    private let useCase: SignUpUseCase

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
    }

    func onDisappear() {
    }

    func onSubmitClicked() async {
        do {
            let error = try await asyncFunction(for: useCase.registerNative(request: RegisterRequest(email: email, password: password)))
            if (error != nil) {
                self.error = error?.localized()
            }
        } catch {
            // no-op: usecase already handling exceptions
        }
    }
}
