package data.repository

import data.remote.response.image.ImageDTO
import data.sqldelight.QuoteEntity
import domain.models.QuoteImage


interface Repository {

    /**
     * Images
     * */
    suspend fun getImagesWithQuote(page: Int = 1): List<ImageDTO>

    suspend fun saveQuoteToFavorite(quote: QuoteImage)

}