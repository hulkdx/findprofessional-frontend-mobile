
extension Task<(), Never> {
    func addTo(_ list: inout [Task<(), Never>]) {
        list.append(self)
    }
}

extension [Task<(), Never>] {
    mutating func cancelAll() {
        self.forEach { task in task.cancel() }
        self.removeAll()
    }
}
