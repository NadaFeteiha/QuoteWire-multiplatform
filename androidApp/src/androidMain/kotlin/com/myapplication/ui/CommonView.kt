//package ui
//
//import androidx.compose.animation.AnimatedContent
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.animation.with
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import di.initKoin
//import com.myapplication.ui.screen.home.HomeScreen
//
//val koin = initKoin()
//
//sealed class Page
//object HomePage : Page()
//
//class NavigationStack<T>(initial: T) {
//    private val stack = mutableStateListOf(initial)
//    fun push(t: T) {
//        stack.add(t)
//    }
//
//    fun back() {
//        if (stack.size > 1) {
//            // Always keep one element on the view stack
//            stack.removeLast()
//        }
//    }
//
//    fun lastWithIndex() = stack.withIndex().last()
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//internal fun CommonView() {
//    val rootPage = HomePage
//    val navigationStack = remember { NavigationStack<Page>(rootPage) }
//
//    Scaffold(topBar = {
//        TopAppBar {
//            IconButton(onClick = {
//                if (navigationStack.lastWithIndex().value != rootPage)
//                    navigationStack.back()
//            }) {
//                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
//            }
//        }
//    }, modifier = Modifier.fillMaxSize()) {
//        AnimatedContent(modifier = Modifier.padding(it),
//            targetState = navigationStack.lastWithIndex(),
//            transitionSpec = {
//                val previousIdx = initialState.index
//                val currentIdx = targetState.index
//                val multiplier = if (previousIdx < currentIdx) 1 else -1
//                slideInHorizontally { w -> multiplier * w } with
//                        slideOutHorizontally { w -> multiplier * -1 * w }
//            }) { (_, page) ->
//            when (page) {
//                is HomePage -> {
//                    HomeScreen(onCreateQuoteClicked = {}, onQuoteClicked = {})
//                }
//
//            }
//        }
//    }
//}
//
