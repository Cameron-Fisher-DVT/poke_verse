package za.co.dvt.pokeverse.presentation.di.featureModules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import za.co.dvt.pokeverse.common.data.local.database.ApplicationDatabase
import za.co.dvt.pokeverse.features.menu.presentation.MenuScreen
import za.co.dvt.pokeverse.features.menu.presentation.MenuScreenViewModel
import za.co.dvt.pokeverse.features.pokedex.data.local.dataSource.PokemonLocalDataSource
import za.co.dvt.pokeverse.features.pokedex.data.local.dataSource.PokemonLocalDataSourceImpl
import za.co.dvt.pokeverse.features.pokedex.data.remote.adapter.PokemonApiAdapter
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSource
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSourceImpl
import za.co.dvt.pokeverse.features.pokedex.data.remote.implementation.PokeApiImpl
import za.co.dvt.pokeverse.features.pokedex.data.repository.PokedexRepositoryImpl
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonInformationUseCase
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.FetchPokemonListUseCase
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.SavePokemonListUseCase
import za.co.dvt.pokeverse.features.pokedex.domain.usecase.UpdatePokemonUseCase
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexFlowManager
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreen
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexScreenViewModel
import za.co.dvt.pokeverse.features.pokedex.presentation.PokedexStatScreenViewModel

val pokedexModule = module {
    factory { PokeApiImpl() }
    factory { PokemonApiAdapter(get()) }
    factory<PokedexRemoteDataSource> {
        PokedexRemoteDataSourceImpl(get())
    }
    factory<PokemonLocalDataSource> {
        PokemonLocalDataSourceImpl(get<ApplicationDatabase>().pokemonDao())
    }
    factory<PokedexRepository> {
        PokedexRepositoryImpl(get(), get())
    }
    factory {
        FetchPokemonListUseCase(get())
    }
    factory {
        SavePokemonListUseCase(get())
    }
    factory {
        FetchPokemonInformationUseCase(get())
    }
    factory {
        UpdatePokemonUseCase(get())
    }

    single { PokedexFlowManager() }

    viewModel {
        PokedexScreenViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        PokedexStatScreenViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel {
        MenuScreenViewModel(get(), get(), get())
    }
}