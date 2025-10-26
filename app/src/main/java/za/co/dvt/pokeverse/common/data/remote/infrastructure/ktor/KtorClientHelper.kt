package za.co.dvt.pokeverse.common.data.remote.infrastructure.ktor

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse

object KtorClientHelper {
    suspend inline fun <reified T> serviceCall(
        call: () -> HttpResponse
    ): NetworkResponse<T> {
        return try {
            NetworkResponse.Success(call.invoke().body<T>())
        } catch (e: RedirectResponseException) {
            Log.e(this.javaClass.name, e.message)
            NetworkResponse.HttpError(e.message)
        } catch (e: ClientRequestException) {
            Log.e(this.javaClass.name, e.message)
            NetworkResponse.HttpError(e.message)
        } catch (e: ServerResponseException) {
            Log.e(this.javaClass.name, e.message)
            NetworkResponse.HttpError(e.message)
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            NetworkResponse.NetworkError(e)
        }
    }
}