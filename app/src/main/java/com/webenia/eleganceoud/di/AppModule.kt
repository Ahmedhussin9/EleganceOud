package com.webenia.eleganceoud.di

import android.content.Context
import android.util.Log
import com.elegance_oud.util.isNetworkAvailable
import com.google.gson.GsonBuilder
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.repositroy.auth.OtpRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.auth.RegisterRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.auth.SignInRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.auth.SignOutRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.category_products.GetCategoryProductsRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.home.GetHomeBestSellingRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.home.GetHomeBrandsRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.home.GetCategoriesRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.home.GetHomeLatestProductsRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.home.GetOurProductsRepositoryImpl
import com.webenia.eleganceoud.data.remote.repositroy.product.GetProductDetailsRepositoryImpl
import com.webenia.eleganceoud.domain.repository.auth.SignOutRepository
import com.webenia.eleganceoud.domain.repository.auth.OtpRepository
import com.webenia.eleganceoud.domain.repository.auth.RegisterRepository
import com.webenia.eleganceoud.domain.repository.auth.SignInRepository
import com.webenia.eleganceoud.domain.repository.category_products.GetCategoryProductsRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeBestSellingRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeBrandsRepository
import com.webenia.eleganceoud.domain.repository.home.GetCategoriesRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeLatestProductsRepository
import com.webenia.eleganceoud.domain.repository.home.GetOurProductsRepository
import com.webenia.eleganceoud.domain.repository.product.GetProductDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): WebServices =
        Retrofit.Builder()
            .baseUrl("https://backend.webenia.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WebServices::class.java)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.e("api", it)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        // Interceptor to use cached data when offline
        val offlineCacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!isNetworkAvailable(context)) {
                request = request.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=86400"
                    ) // 1-day cache
                    .build()
            }
            chain.proceed(request)
        }



        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(offlineCacheInterceptor) // Handles offline caching
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRegisterRepository(webServices: WebServices): RegisterRepository {
        return RegisterRepositoryImpl(webServices)
    }

    @Provides
    fun provideSignInRepository(webServices: WebServices): SignInRepository {
        return SignInRepositoryImpl(webServices)
    }

    @Provides
    fun provideOtpRepository(webServices: WebServices): OtpRepository {
        return OtpRepositoryImpl(webServices)
    }

    @Provides
    fun provideGetOurProducts(webServices: WebServices): GetOurProductsRepository {
        return GetOurProductsRepositoryImpl(webServices)
    }

    @Provides
    fun provideGetHomeCategories(webServices: WebServices): GetCategoriesRepository {
        return GetCategoriesRepositoryImpl(webServices)
    }

    @Provides
    fun provideGetHomeBrands(webServices: WebServices): GetHomeBrandsRepository {
        return GetHomeBrandsRepositoryImpl(webServices)
    }

    @Provides
    fun provideGetHomeBestSelling(webServices: WebServices): GetHomeBestSellingRepository {
        return GetHomeBestSellingRepositoryImpl(webServices)
    }

    @Provides
    fun provideHomeLatestProducts(webServices: WebServices): GetHomeLatestProductsRepository {
        return GetHomeLatestProductsRepositoryImpl(webServices)
    }

    @Provides
    fun provideSignOut(webServices: WebServices): SignOutRepository {
        return SignOutRepositoryImpl(webServices)
    }

    @Provides
    fun provideGetProductDetailsRepository(webServices: WebServices): GetProductDetailsRepository {
        return GetProductDetailsRepositoryImpl(webServices)
    }
    @Provides
    fun provideGetCategoryProductsRepository(webServices: WebServices): GetCategoryProductsRepository {
        return GetCategoryProductsRepositoryImpl(webServices)

    }

}