package data.remote.response


import data.remote.response.QuoteDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(
    @SerialName("count")
    val count: Int?,
    @SerialName("lastItemIndex")
    val lastItemIndex: Int?,
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<QuoteDTO>?,
    @SerialName("totalCount")
    val totalCount: Int?,
    @SerialName("totalPages")
    val totalPages: Int?
)