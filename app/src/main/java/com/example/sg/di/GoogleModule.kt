package com.example.sg.di

import android.app.Application
import com.example.sg.presentation.google_auth.GoogleAuthClient
import com.google.android.gms.auth.api.identity.Identity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleModule {

    @Provides
    @Singleton
    fun provideGoogleAuthClient(app: Application): GoogleAuthClient {
        return GoogleAuthClient(
            context = app,
            oneTapClient = Identity.getSignInClient(app)
        )
    }

}