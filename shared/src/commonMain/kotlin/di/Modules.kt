package di

import data.local.QuoteDataSource
import data.local.QuoteDataSourceImp
import data.remote.service.ImageService
import data.repository.Repository
import data.repository.RepositoryImp
import domain.mappers.ImageMapper
import domain.usecase.GetImagesUseCase
import domain.usecase.ManageFavoriteQuotesUseCase
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
        getUseCasesModules(),
        getSharedModules(),
    )
}

fun getSharedModules() = module {

    single { ImageMapper() }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    factory { ImageService() }

    single<QuoteDataSource> { QuoteDataSourceImp(get()) }

    single<Repository> { RepositoryImp(get(), get()) }

    factory { provideDispatcher() }

}

fun getUseCasesModules() = module {
    single { GetImagesUseCase() }
    single { ManageFavoriteQuotesUseCase() }
}