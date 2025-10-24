package za.co.dvt.pokeverse.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.pokeverse.features.pokedex.data.remote.adapter.PokemonApiAdapter
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSource
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSourceImpl
import za.co.dvt.pokeverse.features.pokedex.data.remote.implementation.PokeApiImpl
import za.co.dvt.pokeverse.features.pokedex.data.repository.PokedexRepositoryImpl
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreenViewModel

val pokedexModule = module {
    factory { PokeApiImpl() }
    factory { PokemonApiAdapter(get()) }
    factory<PokedexRemoteDataSource> {
        PokedexRemoteDataSourceImpl(get())
    }
    factory<PokedexRepository> {
        PokedexRepositoryImpl(get())
    }
    factory {
        FetchPokemonListUseCase(get())
    }
    viewModel { PokedexScreenViewModel(get()) }
}