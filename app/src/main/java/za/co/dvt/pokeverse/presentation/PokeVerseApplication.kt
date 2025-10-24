package za.co.dvt.pokeverse.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import za.co.dvt.pokeverse.presentation.di.featureModules.pokedexModule
import za.co.dvt.pokeverse.presentation.di.presentationModule.presentationModule

class PokeVerseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeVerseApplication)
            androidLogger()
            modules(presentationModule, pokedexModule)
        }
    }
}