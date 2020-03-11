package com.gmail.eamosse.imdb.di

import android.content.Context
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import com.gmail.eamosse.imdb.ui.movies.MovieDetailsViewModel
import com.gmail.eamosse.imdb.ui.trending.TrendingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("API_KEY")) {
        "963fa69378d0f9a586527fa9dfe50806"
    }
    //963fa69378d0f9a586527fa9dfe50806

    single(named("BASE_URL")) {
        "https://api.themoviedb.org/3/"
    }

    single(named("APP_PREFS")) {
        androidContext().getSharedPreferences("app_private", Context.MODE_PRIVATE)
    }

    viewModel {
        HomeViewModel(repository = get())
    }
    viewModel {
        TrendingViewModel(repository = get())
    }
    viewModel {
        MovieDetailsViewModel(repository = get())
    }
}