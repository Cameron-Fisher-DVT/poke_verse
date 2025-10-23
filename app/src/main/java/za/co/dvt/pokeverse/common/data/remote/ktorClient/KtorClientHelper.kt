package za.co.dvt.pokeverse.common.data.remote.ktorClient

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse

object KtorClientHelper {
    suspend inline fun <reified T> serviceCall(
        call: () -> HttpResponse
    ): T {
        return try {
            call.invoke().body<T>()
        } catch (e: RedirectResponseException) {
            // 3XX Response
            Log.e(this.javaClass.name, e.message)
            throw e
        } catch (e: ClientRequestException) {
            // 4XX Responses
            Log.e(this.javaClass.name, e.message)
            throw e
        } catch (e: ServerResponseException) {
            // 5XX Responses
            Log.e(this.javaClass.name, e.message)
            throw e
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            throw e
        }
    }
}