import data.DatabaseDriverFactory
import data.sqldelight.QuoteDB
import di.getSharedModules
import di.getUseCasesModules
import org.koin.core.context.startKoin
import org.koin.dsl.module


val iosModule = module {
    // Define the singleton instance of DatabaseDriverFactory
    single { DatabaseDriverFactory() }

    // Define the singleton instance of Database
    single { QuoteDB(get()) }
}

fun initKoin(){
    startKoin {
        modules(
            iosModule,
            getSharedModules(),
            getUseCasesModules()
        )
    }
}

