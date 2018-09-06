package my.project.chongieball.footballmatch.injection.module

import dagger.Module
import dagger.Provides
import my.project.chongieball.footballmatch.BuildConfig
import my.project.chongieball.footballmatch.data.network.Service
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by chongieball on 04/09/18.
 */
@Module
class NetworkModule {
    val CONNECT_TIMEOUT_IN_MS = 200000

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS.toLong(), TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun getService(retrofit: Retrofit) : Service = retrofit.create(Service::class.java)
}