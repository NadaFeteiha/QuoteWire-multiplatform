package di

import data.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import provideDispatcher

actual fun platformModule(): Module = module {
//    single { DatabaseDriverFactory() }
    single { getSharedModules() }

}
