package za.co.dvt.pokeverse.common.data.local.model.search

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["searchHistoryId"], unique = true)], tableName = "SearchHistory")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val searchHistoryId: Int = 0,
    val query: String
)