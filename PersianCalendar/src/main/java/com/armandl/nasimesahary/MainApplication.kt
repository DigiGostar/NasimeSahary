package com.armandl.nasimesahary

import com.armandl.nasimesahary.di.DaggerAppComponent
import com.armandl.nasimesahary.utils.Utils

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MainApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoNaskhArabic-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        ReleaseDebugDifference.mainApplication(this)
        Utils.initUtils(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
}
