import SwiftUI
import Foundation
import shared


@UIApplicationMain
class MainApp: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    
    private lazy var rootRouterContext = {
        KoinFactoryIos().doInitKoin { module in
            module.addStripePaymentFactoryIos { StripePaymentFactoryImpl() }
        }
        return DecomposeHelperKt.getRoot()
    }()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        let mainViewController = MainViewControllerKt.MainViewController(root: rootRouterContext)
        window?.rootViewController = mainViewController
        window?.makeKeyAndVisible()
        return true
    }
    
    func applicationDidBecomeActive(_ application: UIApplication) {
        rootRouterContext.resume()
    }
    
    func applicationWillResignActive(_ application: UIApplication) {
        rootRouterContext.stop()
    }
    
    func applicationWillTerminate(_ application: UIApplication) {
        rootRouterContext.destroy()
    }
}
