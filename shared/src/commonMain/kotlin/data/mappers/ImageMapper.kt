package data.mappers

import data.remote.response.image.ImageDTO
import data.sqldelight.QuoteEntity
import domain.models.QuoteImage

internal fun ImageDTO.toDomain(): QuoteImage {
    return QuoteImage(
        id = id ?: "",
        imageURL = urls?.regular ?: "",
        downloadLink = links?.download ?: ""
    )
}

internal fun List<ImageDTO>.toDomain()  = map { it.toDomain() }