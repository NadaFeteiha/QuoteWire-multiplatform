package domain.mappers

import data.remote.response.QuoteDTO
import domain.models.Quote

class QuoteMapper  constructor() : Mapper<QuoteDTO, Quote> {
    override fun map(input: QuoteDTO): Quote {
        return Quote(
            id = input.id ?: "",
            author = input.author ?: "",
            content = input.content ?: ""
        )
    }
}