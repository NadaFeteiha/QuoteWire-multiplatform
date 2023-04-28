package domain.usecase

import data.repository.Repository
import domain.mappers.ImageMapper
import domain.models.QuoteImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetImagesUseCase : KoinComponent {

    private val repository: Repository by inject()
    private val imageMapper: ImageMapper by inject()

    suspend fun getImagesWithQuote(): List<QuoteImage> {
        return getImages(keyword = "quote")
    }

    suspend fun getImages(): List<QuoteImage> {
        return getImages(keyword = "background")
    }

    private suspend fun getImages(keyword: String): List<QuoteImage> {
        val result = repository.getImagesWithQuote(keyword = keyword)
        return result.map { imageMapper.map(it) }
    }
}