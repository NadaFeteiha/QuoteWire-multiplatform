package data.repository

import data.remote.response.image.ImageDTO
import data.remote.service.ImageService
import data.repository.Repository

class RepositoryImp constructor(
    private val imageService: ImageService,
) : Repository {


    /**
     * Images
     * */
    override suspend fun getImagesWithQuote(page: Int): List<ImageDTO> {
        return imageService.getQuoteWithImage(page = page).results ?: emptyList()
    }

}