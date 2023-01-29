import SwiftUI
import shared
import KMPNativeCoroutinesAsync

@MainActor
class SignUpViewModel: ObservableObject {
    private let useCase: SignUpUseCase

    init(_ useCase: SignUpUseCase) {
        self.useCase = useCase
    }

    func onSubmitClicked() {
    }
}
