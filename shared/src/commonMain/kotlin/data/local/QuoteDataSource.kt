package data.local

import data.sqldelight.QuoteEntity
import domain.models.QuoteImage

interface QuoteDataSource {
    fun getAllQuotes(): List<QuoteEntity>

    fun insertQuote(quote: QuoteImage)

    fun deleteQuote(quoteId: String)

    fun getQuoteById(quoteId: String): QuoteEntity?

}