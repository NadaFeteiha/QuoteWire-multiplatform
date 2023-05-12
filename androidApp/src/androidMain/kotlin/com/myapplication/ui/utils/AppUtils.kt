package com.myapplication.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent


fun copyToClipboard(context: Context, text: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("text", text)
    clipboardManager.setPrimaryClip(clip)
}

fun shareImage(image: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Image URL")
        putExtra(Intent.EXTRA_TEXT, image)
    }
    val shareIntent = Intent.createChooser(intent, null)
    context.startActivity(shareIntent)
}