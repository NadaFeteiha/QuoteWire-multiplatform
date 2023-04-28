package domain.mappers

import data.remote.response.image.ImageDTO
import domain.models.QuoteImage

class ImageMapper : Mapper<ImageDTO, QuoteImage> {

    override fun map(input: ImageDTO): QuoteImage {
        return QuoteImage(
            id = input.id ?: "",
            imageURL = input.urls?.regular ?: "",
            downloadLink = input.links?.download ?: ""
        )
    }
}