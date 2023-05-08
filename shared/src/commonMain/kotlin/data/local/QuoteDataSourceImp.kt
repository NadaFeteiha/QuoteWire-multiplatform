package data.local

import data.DatabaseDriverFactory
import data.sqldelight.QuoteDB
import data.sqldelight.QuoteEntity
import domain.models.QuoteImage

class QuoteDataSourceImp(
    private val databaseDriverFactory: DatabaseDriverFactory
) : QuoteDataSource {

    private val database = QuoteDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.quoteDBQueries

    override fun getAllQuotes(): List<QuoteEntity> {
        return dbQuery.getAllQuote().executeAsList()
    }

    override fun insertQuote(quote: QuoteImage) {
        dbQuery.insertQuote(
            id = quote.id,
            imageUrl = quote.imageURL,
        )
    }

    override fun deleteQuote(quoteId: String) {
        dbQuery.deleteQuote(quoteId)
    }

    override fun getQuoteById(quoteId: String): QuoteEntity? {
        return dbQuery.getQuoteById(quoteId).executeAsOneOrNull()
    }

}