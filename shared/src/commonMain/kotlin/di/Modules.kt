package di

import data.remote.service.ImageServiceImpl
import data.repository.Repository
import domain.RepositoryImp
import domain.mappers.ImageMapper
import domain.usecase.GetImagesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import provideDispatcher


fun getSharedModules() = module {

    single { ImageMapper() }

    single { GetImagesUseCase() }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    factory { ImageServiceImpl() }

    single<Repository> { RepositoryImp(get()) }

    factory { provideDispatcher() }

}

