package za.co.dvt.pokeverse.features.pokedex.data.repository

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
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.dto.PokemonResultDto
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSourceImpl


class PokedexRepositoryImplTest {
    @MockK
    private lateinit var remoteDataSource: PokedexRemoteDataSourceImpl

    @InjectMockKs
    private lateinit var sut: PokedexRepositoryImpl

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
    @DisplayName("Should Return Success When Fetch Pokemon Response List Is Called")
    fun shouldReturnSuccessWhenFetchPokemonResponseListIsCalled() = runTest {
        coEvery { remoteDataSource.fetchPokemonResponseList() } returns ApiResponse.Success(mockPokemonListResponse)

        val result = sut.fetchPokemonList()

        assertTrue(result is Result.Success)
        result as Result.Success
        assertEquals(1, result.data.size)
        assertEquals("bulbasaur", result.data[0].name)
    }

    @Test
    @DisplayName("Should Return Error When Fetch Pokemon Response List Is Called")
    fun shouldReturnErrorWhenFetchPokemonResponseListIsCalled() = runTest {
        val errorMessage = "Network error"
        coEvery { remoteDataSource.fetchPokemonResponseList() } returns ApiResponse.Error(errorMessage)

        val result = sut.fetchPokemonList()

        assertTrue(result is Result.Error)
        result as Result.Error
        assertEquals(errorMessage, result.message)
    }
}