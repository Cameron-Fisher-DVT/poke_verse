package za.co.dvt.pokeverse.common.manager.resourceManager

import android.app.Application
import androidx.annotation.StringRes

class ResourceManagerImpl(
    private val application: Application
) : ResourceManager {
    override fun getString(@StringRes stringResId: Int): String {
        return application.getString(stringResId)
    }
}