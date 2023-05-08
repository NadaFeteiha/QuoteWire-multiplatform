package data

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import data.sqldelight.QuoteDB
import android.content.Context

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(QuoteDB.Schema, context, "QuoteDB.db")
    }
}