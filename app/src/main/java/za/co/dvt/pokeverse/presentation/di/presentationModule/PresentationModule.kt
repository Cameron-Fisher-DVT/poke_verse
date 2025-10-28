package za.co.dvt.pokeverse.presentation.di.presentationModule

import org.koin.dsl.module
import za.co.dvt.pokeverse.common.data.local.database.ApplicationDatabase
import za.co.dvt.pokeverse.common.data.local.database.JetpackRoomDBImpl
import za.co.dvt.pokeverse.common.data.local.preferences.JetpackDataStoreImpl
import za.co.dvt.pokeverse.common.data.local.preferences.PreferencesManager
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManager
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManagerImpl
import za.co.dvt.pokeverse.presentation.navigation.DefaultNavigator
import za.co.dvt.pokeverse.presentation.navigation.Destination
import za.co.dvt.pokeverse.presentation.navigation.Navigator

val presentationModule = module {
    single<ResourceManager> {
        ResourceManagerImpl(get())
    }

    single<ApplicationDatabase> {
        JetpackRoomDBImpl.getDatabase(get())
    }

    single<Navigator> {
        DefaultNavigator(startDestination = Destination.HomeGraph.route)
    }

    single<PreferencesManager> {
        JetpackDataStoreImpl(get())
    }
}