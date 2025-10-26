package za.co.dvt.pokeverse.common.data.local.common

sealed class DatabaseResponse<T> {
    data class Success<T>(val data: T) : DatabaseResponse<T>()
    data class Error<T>(val message: String) : DatabaseResponse<T>()
}