package domain.usecase

import data.repository.Repository
import domain.mappers.ImageMapper
import domain.models.QuoteImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetImagesUseCase : KoinComponent {

    private val repository: Repository by inject()
    private val imageMapper: ImageMapper by inject()

    suspend operator fun invoke(): List<QuoteImage> {
        val result = repository.getImagesWithQuote()
        return result.map { imageMapper.map(it) }
    }
}