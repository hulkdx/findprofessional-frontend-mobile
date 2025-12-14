import Foundation
import UIKit
import shared
import StripePaymentSheet


class StripePaymentFactoryImpl: StripePaymentFactoryIos {
    func onCreate(networkResult: CoreCreateBookingResponse, onResult: @escaping (shared.PaymentSheetResult) -> Void) -> UIView {
        return StripePaymentSheetView(networkResult: networkResult, onResult: onResult)
    }
}

private final class StripePaymentSheetView: UIView {
    private let networkResult: CoreCreateBookingResponse
    private let onResult: (shared.PaymentSheetResult) -> Void
    private var paymentSheet: PaymentSheet?
    private var hasPresented = false

    init(networkResult: CoreCreateBookingResponse, onResult: @escaping (shared.PaymentSheetResult) -> Void) {
        self.networkResult = networkResult
        self.onResult = onResult
        super.init(frame: .zero)
        backgroundColor = .clear
        setUpPaymentSheet()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func didMoveToWindow() {
        super.didMoveToWindow()
        presentIfNeeded()
    }

    private func setUpPaymentSheet() {
        STPAPIClient.shared.publishableKey = networkResult.publishableKey

        var configuration = PaymentSheet.Configuration()
        configuration.merchantDisplayName = "My merchant name"
        configuration.customer = .init(
            id: networkResult.customer,
            customerSessionClientSecret: networkResult.customerSessionClientSecret
        )
        configuration.allowsDelayedPaymentMethods = true

        paymentSheet = PaymentSheet(
            paymentIntentClientSecret: networkResult.paymentIntent,
            configuration: configuration
        )
    }

    private func presentIfNeeded() {
        guard !hasPresented,
              window != nil,
              let viewController = findViewController(),
              let paymentSheet = paymentSheet
        else { return }

        hasPresented = true
        paymentSheet.present(from: viewController) { [weak self] result in
            DispatchQueue.main.async {
                self?.handleResult(result)
            }
        }
    }

    private func handleResult(_ result: StripePaymentSheet.PaymentSheetResult) {
        switch result {
        case .completed:
            onResult(shared.PaymentSheetResult.Completed.shared)
        case .canceled:
            onResult(shared.PaymentSheetResult.Canceled.shared)
        case .failed(let error):
            let throwable = KotlinThrowable(message: error.localizedDescription)
            onResult(shared.PaymentSheetResult.Failed(error: throwable))
        @unknown default:
            let throwable = KotlinThrowable(message: "Unknown payment result")
            onResult(shared.PaymentSheetResult.Failed(error: throwable))
        }
    }
}

private extension UIView {
    func findViewController() -> UIViewController? {
        var responder: UIResponder? = self
        while let currentResponder = responder {
            if let viewController = currentResponder as? UIViewController {
                return viewController
            }
            responder = currentResponder.next
        }
        return nil
    }
}
