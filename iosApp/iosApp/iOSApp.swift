import SwiftUI
import shared

@main
struct iOSApp: App {

     init(){
         DiKoinKt.doInitKoin()
     }
    
	var body: some Scene {
		WindowGroup {
			HomeScreen()
		}
	}
}
