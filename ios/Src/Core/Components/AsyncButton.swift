import SwiftUI

struct AsyncButton<Label> : View where Label: View  {
    let action: () async -> Void
    
    @ViewBuilder
    let label: () -> Label
    
    @State private var task: Task<Void, Error>?
    
    var body: some View {
        Button(action: {
            task?.cancel()
            task = Task {
                let started = Date.timeIntervalSinceReferenceDate
                await action()
                let end = Date.timeIntervalSinceReferenceDate
                if (started - end < 1) {
                    try await Task.sleep(nanoseconds: 500_000)
                }
                task = nil
            }
        }, label: label)
        .onDisappear {
            task?.cancel()
        }
    }
}
