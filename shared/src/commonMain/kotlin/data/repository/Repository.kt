package data.repository

import data.remote.response.image.ImageDTO


interface Repository {

    /**
     * Images
     * */
    suspend fun getImagesWithQuote(page: Int = 1): List<ImageDTO>

}