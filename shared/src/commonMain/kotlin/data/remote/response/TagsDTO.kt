package data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsDTO(
    @SerialName("dateAdded")
    val dateAdded: String?,
    @SerialName("dateModified")
    val dateModified: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("quoteCount")
    val quoteCount: Int?,
    @SerialName("slug")
    val slug: String?
)