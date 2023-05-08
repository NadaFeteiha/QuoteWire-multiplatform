package data.repository

import data.local.QuoteDataSource
import data.remote.response.image.ImageDTO
import data.remote.service.ImageService
import domain.mappers.toDomain
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
        quoteDataSource.insertQuote(quote)
    }

    override suspend fun getFavoriteQuoteById(quoteId: String): QuoteImage? {
        return quoteDataSource.getQuoteById(quoteId = quoteId)?.toDomain()
    }

    override suspend fun getAllFavoriteQuotes(): List<QuoteImage> {
        return quoteDataSource.getAllQuotes().toDomain()
    }

    override suspend fun removeQuoteFromFavorite(quoteId: String) {
        quoteDataSource.deleteQuote(quoteId = quoteId)
    }

}