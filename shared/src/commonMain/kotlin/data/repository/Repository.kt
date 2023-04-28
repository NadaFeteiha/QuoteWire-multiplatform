package data.repository

import data.remote.response.image.ImageDTO


interface Repository {

    /**
     * Images
     * */
    suspend fun getImagesWithQuote(keyword: String): List<ImageDTO>

}