package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.feature_weather.data.data_resource.FavoritesDatabase
import com.example.weatherapp.feature_weather.data.network.CountryApi
import com.example.weatherapp.feature_weather.data.network.WeatherApi
import com.example.weatherapp.feature_weather.data.repository.CountryRepository
import com.example.weatherapp.feature_weather.data.repository.RepositoryImp
import com.example.weatherapp.feature_weather.data.repository.WeatherRepository
import com.example.weatherapp.feature_weather.data.utils.Constants
import com.example.weatherapp.feature_weather.domain.repository.Repository
import com.example.weatherapp.feature_weather.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideFavoritesDatabase(app: Application):FavoritesDatabase{
        return Room.databaseBuilder(
            app,
            FavoritesDatabase::class.java,
            FavoritesDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(db:FavoritesDatabase):Repository{
        return RepositoryImp(db.favoritesDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCases(repository:Repository):FavoritesUseCases{
        return FavoritesUseCases(
            addFavorite = AddFavorite(repository),
            deleteFavorite = DeleteFavorite(repository),
            getFavorites = GetFavorites(repository),
            getFavoriteById = GetFavoriteById(repository)
        )
    }

    @Singleton
    @Provides
    fun provideWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
    @Singleton
    @Provides
    fun provideWeatherApiRepository(api:WeatherApi) = WeatherRepository(api)


    @Singleton
    @Provides
    fun provideCountryApi():CountryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_COUNTRIES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCountriesApiRepository(api:CountryApi) = CountryRepository(api)

}