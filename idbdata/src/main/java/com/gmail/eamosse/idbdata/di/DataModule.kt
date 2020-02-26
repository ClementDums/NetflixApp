package com.gmail.eamosse.idbdata.di

import android.content.Context
import androidx.room.Room
import com.gmail.eamosse.idbdata.BuildConfig
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.databases.IdbDataBase
import com.gmail.eamosse.idbdata.repository.MovieRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        NetworkXConfig.buildHttpClient(
            apiKey = get(named("API_KEY")),
            dao = get()
        )
    }
    single() {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(get(named("BASE_URL")) as String)
            .client(get())
            .build()
            .create(MovieService::class.java)
    }

    single {
        LocalDataSource()
    }

    single {
        OnlineDataSource(get())
    }

    single {
        MovieRepository()
    }

    single {
        DatabaseConfig.buildDatabase(get())
    }

    single {
        DatabaseConfig.getTokenDao(get())
    }
}

private object DatabaseConfig {
    fun buildDatabase(context: Context): IdbDataBase {
        return Room.databaseBuilder(
            context,
            IdbDataBase::class.java, "idb_database.db"
        ).build()
    }

    fun getTokenDao(db: IdbDataBase): TokenDao = db.tokenDao()
}

private object NetworkXConfig {
    fun buildHttpClient(apiKey: String, dao: TokenDao): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BasicInterceptor(apiKey).requestInterceptor)
            .addInterceptor(
                TokenInterceptor(
                    dao
                ).requestInterceptor
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    }
}

/**
 * Un intercepteur qui modifie toutes les requêtes HTTP en y ajoutant un token
 * Le token est récupéré dans la BDD
 */
private class TokenInterceptor(
    private val dao: TokenDao
) {

    val requestInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder().url(originalHttpUrl.newBuilder().build())
        dao.retrieve()?.token?.let {
            requestBuilder.addHeader("Authorization", it)
        }
        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }
}

/**
 * Intercepteur qui modifie l'entête de chaque requête
 */
class BasicInterceptor(
    apiKey: String
) {
    val requestInterceptor = Interceptor { chain ->
        val original = chain.request()

        val url = original.url
            .newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language",Locale.getDefault().country)
            .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }
}