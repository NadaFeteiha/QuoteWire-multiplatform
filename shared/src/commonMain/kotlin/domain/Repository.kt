package domain

import data.remote.response.image.ImageDTO
import data.sqldelight.QuoteEntity
import domain.models.QuoteImage
import kotlinx.coroutines.flow.Flow


interface Repository {

    /**
     * Images
     * */
    suspend fun getImagesWithQuote(page: Int = 1): List<ImageDTO>

    suspend fun saveQuoteToFavorite(quote: QuoteImage)

    suspend fun getFavoriteQuoteById(quoteId: String): QuoteImage?

    suspend fun getAllFavoriteQuotes(): List<QuoteImage>

    suspend fun removeQuoteFromFavorite(quoteId: String)

}