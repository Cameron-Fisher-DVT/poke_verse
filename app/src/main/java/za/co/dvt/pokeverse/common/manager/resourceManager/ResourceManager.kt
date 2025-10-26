package za.co.dvt.pokeverse.common.manager.resourceManager

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringResId: Int): String
}