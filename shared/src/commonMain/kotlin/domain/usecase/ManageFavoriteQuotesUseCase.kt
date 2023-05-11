package domain.usecase

import domain.Repository
import domain.models.QuoteImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ManageFavoriteQuotesUseCase : KoinComponent {

    private val repository: Repository by inject()

    suspend fun saveFavoriteQuote(quoteImage: QuoteImage) {
        repository.saveQuoteToFavorite(quoteImage)
    }

    suspend fun getAllFavoriteQuote(): List<QuoteImage> {
        return repository.getAllFavoriteQuotes()
    }

    suspend fun getFavoriteQuoteByID(id: String): QuoteImage? {
        return repository.getFavoriteQuoteById(quoteId = id)
    }

    suspend fun removeQuoteFromFavorite(id: String) {
        repository.removeQuoteFromFavorite(quoteId = id)
    }

}