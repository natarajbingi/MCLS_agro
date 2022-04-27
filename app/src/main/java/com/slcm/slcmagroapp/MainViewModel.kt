package com.slcm.slcmagroapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainViewModel(application: Application) : AndroidViewModel(application) {
}