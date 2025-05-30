package com.example.tibiaclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // It makes Hilt works on the entire APP
class MyApp : Application()
