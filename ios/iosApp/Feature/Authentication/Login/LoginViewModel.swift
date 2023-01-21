import SwiftUI
import Combine
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel: ObservableObject {
    
    private let repository: LoginRepository
    
    @Published
    var data = ""
    
    
    @Published
    var showSignUp = false
    
    init(repository: LoginRepository) {
        self.repository = repository
        
//        Task {
//            do {
//                for try await data in asyncStream(for: repository.getFlowNative()) { self.data = data }
//            } catch {
//                print("Failed with error: \(error)")
//            }
//        }
    }
    
    func signInButtonClicked() {
        
    }
    
    func signUpButtonClicked() {
        showSignUp = true
    }
}
