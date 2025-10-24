package za.co.dvt.pokeverse.presentation.di.presentationModule

import org.koin.dsl.module
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManager
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManagerImpl

val presentationModule = module {
    single<ResourceManager> {
        ResourceManagerImpl(get())
    }
}