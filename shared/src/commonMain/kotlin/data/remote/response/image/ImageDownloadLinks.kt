package data.remote.response.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ImageDownloadLinks(
    @SerialName("download")
    val download: String?,
    @SerialName("download_location")
    val downloadLocation: String?,
    @SerialName("html")
    val html: String?,
    @SerialName("self")
    val self: String?
)