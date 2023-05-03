package com.myapplication.ui.screen.quoteView

import androidx.lifecycle.SavedStateHandle
import java.net.URLDecoder

class QuoteArgs(savedStateHandle: SavedStateHandle) {

    val quoteId: String = checkNotNull(savedStateHandle[Quote_ID_ARG])
    val quoteURL: String = URLDecoder.decode(savedStateHandle[Quote_URL_ARG])
    val quoteDownloadLink: String = URLDecoder.decode(savedStateHandle[Quote_DOWNLOAD_ARG])

    companion object {
        const val Quote_ID_ARG = "quoteOwnerId"
        const val Quote_URL_ARG = "quoteOwnerURL"
        const val Quote_DOWNLOAD_ARG = "quoteOwnerDOWNLOAD"
    }
}