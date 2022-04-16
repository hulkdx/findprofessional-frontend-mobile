import SwiftUI
import Combine
import shared
import KMPNativeCoroutinesAsync

struct ContentView: View {
    @StateObject
    var viewModel = ViewModel(repository: LoginRepository())

	var body: some View {
        Text(viewModel.data)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

@MainActor
class ViewModel: ObservableObject {
    
    private let repository: LoginRepository
    
    @Published
    var data = ""
    
    init(repository: LoginRepository) {
        self.repository = repository
        
        Task {
            do {
                for try await data in asyncStream(for: repository.getFlowNative()) { self.data = data }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}
