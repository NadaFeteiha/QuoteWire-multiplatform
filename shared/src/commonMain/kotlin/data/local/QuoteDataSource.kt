package data.local

import data.sqldelight.QuoteEntity
import domain.models.QuoteImage

interface QuoteDataSource {
    fun getAllQuotes(): List<QuoteEntity>

    fun insertLaunch(quote: QuoteImage)
}