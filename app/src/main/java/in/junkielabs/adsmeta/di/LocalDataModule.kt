package `in`.junkielabs.adsmeta.di

import `in`.junkielabs.adsmeta.data.local.ads.LocalAdsSource
import `in`.junkielabs.adsmeta.data.local.ads.domain.AdsRepositoryImpl
import `in`.junkielabs.adsmeta.domain.ads.repository.AdsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Niraj on 21-12-2022.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

   /* @Provides
    fun provideAdsSource(): LocalAdsSource = LocalAdsSource()
    
    @Provides
    fun provideAdsRepository(): AdsRepository = AdsRepositoryImpl()
*/

    @Binds
    abstract fun bindAdsRepository(impl: AdsRepositoryImpl): AdsRepository

//= AdsRepositoryImpl()
}