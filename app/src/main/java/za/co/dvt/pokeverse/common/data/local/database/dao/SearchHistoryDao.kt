package za.co.dvt.pokeverse.common.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import za.co.dvt.pokeverse.common.data.local.model.search.SearchHistoryEntity

@Dao
interface SearchHistoryDao: BaseDao<SearchHistoryEntity> {
    @Query("SELECT * FROM searchhistory ORDER BY searchHistoryId DESC LIMIT 10")
    fun fetchLastTenSearchHistoryQueries(): List<SearchHistoryEntity>
}