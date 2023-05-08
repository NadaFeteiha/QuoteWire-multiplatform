package di

import com.squareup.sqldelight.db.SqlDriver
import data.DatabaseDriverFactory
import data.local.QuoteDataSource
import data.local.QuoteDataSourceImp
import data.remote.service.ImageService
import data.repository.Repository
import data.repository.RepositoryImp
import data.sqldelight.QuoteDB
import domain.mappers.ImageMapper
import domain.usecase.GetImagesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import provideDispatcher

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        getSharedModules()
    )
}

fun getSharedModules() = module {

    single { ImageMapper() }

    single { GetImagesUseCase() }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    factory { ImageService() }

    single<QuoteDataSource> { QuoteDataSourceImp(get()) }

    single<Repository> { RepositoryImp(get(), get()) }

    factory { provideDispatcher() }

}

