package za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.adapter.PokemonApiAdapter
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.dto.PokemonResultDto

class PokedexRemoteDataSourceImplTest {
    @MockK
    private lateinit var pokemonApiAdapter: PokemonApiAdapter

    @InjectMockKs
    private lateinit var sut: PokedexRemoteDataSourceImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private val mockPokemonListResponse = PokemonListResponse(
        count = 1,
        next = "",
        previous = null,
        pokemonResultDtoList = listOf(
            PokemonResultDto(
                name = "bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            )
        )
    )

    @Test
    @DisplayName("Should Return Success When Fetch Pokemon List Response Is Called")
    fun shouldReturnSuccessWhenFetchPokemonListResponseIsCalled() = runTest {
        coEvery { pokemonApiAdapter.fetchPokemonListResponse(any<Int>(), any<Int>()) } returns NetworkResponse.Success(mockPokemonListResponse)

        val result = sut.fetchPokemonListResponse(1, 1)

        assertTrue(result is ApiResponse.Success)
        result as ApiResponse.Success
        assertEquals(mockPokemonListResponse, result.data)
        assertEquals(1, result.data.count)
        assertEquals("bulbasaur", result.data.pokemonResultDtoList[0].name)
    }

    @Test
    @DisplayName("Should Return Error When Fetch Pokemon List Response Fails")
    fun shouldReturnErrorWhenFetchPokemonListResponseFails() = runTest {
        val errorMessage = "Network error"
        coEvery { pokemonApiAdapter.fetchPokemonListResponse(any<Int>(), any<Int>()) } returns NetworkResponse.NetworkError(Exception(errorMessage))

        val result = sut.fetchPokemonListResponse(1, 1)

        assertTrue(result is ApiResponse.Error)
        result as ApiResponse.Error
        assertEquals(errorMessage, result.message)
    }
}
