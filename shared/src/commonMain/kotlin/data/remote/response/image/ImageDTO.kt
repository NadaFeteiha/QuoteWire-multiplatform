package data.remote.response.image


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDTO(
    @SerialName("id")
    val id: String?,
    @SerialName("likes")
    val likes: Int?,
    @SerialName("links")
    val links: ImageDownloadLinks?,
    @SerialName("urls")
    val urls: ImageURLs?
)