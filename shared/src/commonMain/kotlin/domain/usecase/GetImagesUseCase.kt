package domain.usecase

import data.mappers.toDomain
import domain.Repository
import domain.models.QuoteImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetImagesUseCase : KoinComponent {

    private val repository: Repository by inject()

    private var page = 1

    suspend operator fun invoke(): List<QuoteImage> {
        //need to return uiState loading ,error, success
        return try {
            val result = repository.getImagesWithQuote(page = page)
            page++
            result.toDomain()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    suspend fun getMoreImages(): List<QuoteImage> {
        return try {
            val result = repository.getImagesWithQuote(page = page)
            page++
            result.toDomain()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}