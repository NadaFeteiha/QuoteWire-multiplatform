package domain

import data.remote.response.image.ImageDTO
import data.remote.service.ImageServiceImpl
import data.repository.Repository

class RepositoryImp  constructor(
    private val imageService: ImageServiceImpl,
) : Repository {


    /**
     * Images
     * */
    override suspend fun getImagesWithQuote(keyword: String): List<ImageDTO> {
        return imageService.getQuoteWithImage(query = keyword).results ?: emptyList()
    }

}