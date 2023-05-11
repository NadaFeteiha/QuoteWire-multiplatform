package data.mappers

import data.sqldelight.QuoteEntity
import domain.models.QuoteImage

internal fun QuoteEntity.toDomain(): QuoteImage {
    return QuoteImage(
        id = id,
        imageURL = imageUrl, downloadLink = ""
    )
}

internal fun List<QuoteEntity>.toDomain() = map { it.toDomain() }
