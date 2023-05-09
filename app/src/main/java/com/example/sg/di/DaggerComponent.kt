package com.example.sg.di

import com.example.sg.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ExampleModule::class])
interface DaggerComponent {
    fun inject(activity: MainActivity)
}

