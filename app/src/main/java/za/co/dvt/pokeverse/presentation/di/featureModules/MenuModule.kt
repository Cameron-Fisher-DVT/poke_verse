package za.co.dvt.pokeverse.presentation.di.featureModules

import org.koin.dsl.module
import za.co.dvt.pokeverse.features.menu.data.local.dataSource.MenuManagementLocalDataSource
import za.co.dvt.pokeverse.features.menu.data.local.dataSource.MenuManagementLocalDataSourceImpl
import za.co.dvt.pokeverse.features.menu.data.repository.MenuManagementRepositoryImpl
import za.co.dvt.pokeverse.features.menu.domain.repository.MenuManagementRepository
import za.co.dvt.pokeverse.features.menu.domain.usecase.FetchDarkModeUseCase
import za.co.dvt.pokeverse.features.menu.domain.usecase.SaveDarkModeUseCase

val menuModule = module {
    factory<MenuManagementLocalDataSource> {
        MenuManagementLocalDataSourceImpl(get())
    }
    factory<MenuManagementRepository> {
        MenuManagementRepositoryImpl(get())
    }
    factory {
        SaveDarkModeUseCase(get())
    }
    factory {
        FetchDarkModeUseCase(get())
    }
}