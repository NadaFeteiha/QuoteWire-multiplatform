package data.repository

import data.local.QuoteDataSource
import data.local.QuoteDataSourceImp
import data.remote.response.image.ImageDTO
import data.remote.service.ImageService
import domain.models.QuoteImage

class RepositoryImp constructor(
    private val imageService: ImageService,
    private val quoteDataSource: QuoteDataSource
) : Repository {


    /**
     * Images
     * */
    override suspend fun getImagesWithQuote(page: Int): List<ImageDTO> {
        return imageService.getQuoteWithImage(page = page).results ?: emptyList()
    }

    override suspend fun saveQuoteToFavorite(quote: QuoteImage) {
        quoteDataSource.insertLaunch(quote)
    }

}