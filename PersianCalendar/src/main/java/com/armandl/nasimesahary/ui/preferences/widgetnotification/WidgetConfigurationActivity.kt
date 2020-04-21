package com.armandl.nasimesahary.ui.preferences.widgetnotification

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.armandl.nasimesahary.R
import com.armandl.nasimesahary.databinding.WidgetPreferenceLayoutBinding
import com.armandl.nasimesahary.utils.UpdateUtils
import com.armandl.nasimesahary.utils.Utils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class WidgetConfigurationActivity : AppCompatActivity() {

    private fun finishAndSuccess() {
        val extras = intent.extras
        if (extras != null) {
            val appwidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)
            setResult(Activity.RESULT_OK, Intent()
                    .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidgetId))
        }
        Utils.updateStoredPreference(this)
        UpdateUtils.update(this, false)
        finish()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Don't replace below with appDependency.getSharedPreferences() ever
        // as the injection won't happen at the right time
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        setTheme(Utils.getThemeFromName(Utils.getThemeFromPreference(prefs)))

        Utils.applyAppLanguage(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<WidgetPreferenceLayoutBinding>(this, R.layout.widget_preference_layout)

        supportFragmentManager.transaction {
            add(R.id.preference_fragment_holder, FragmentWidgetNotification(), "TAG")
        }
        binding.addWidgetButton.setOnClickListener { finishAndSuccess() }
    }
}
