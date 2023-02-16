import SwiftUI
import shared

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
            let error = try await useCase.register(request: AuthRequest(email: email, password: password))
            if (error != nil) {
                self.error = error?.localized()
            }
        } catch {
            // no-op: usecase already handling exceptions
        }
    }
}
