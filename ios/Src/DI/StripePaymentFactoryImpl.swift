import Foundation
import UIKit
import shared
import StripePaymentSheet

class StripePaymentFactoryImpl: StripePaymentFactoryIos {
    func onCreate(networkResult: CoreCreateBookingResponse, onResult: @escaping (PaymentSheetResult) -> Void) -> UIView {
    }
}
