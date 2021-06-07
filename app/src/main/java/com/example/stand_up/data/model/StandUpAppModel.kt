package com.example.stand_up.data.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.example.stand_up.data.responses.ResourcesResponse
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.example.stand_up.R
import com.example.stand_up.data.networkvos.BreedVO
import com.example.stand_up.data.networkvos.ErrorVO
import com.example.stand_up.data.networkvos.ResourcesVO
import com.example.stand_up.util.Constants
import com.example.stand_up.util.StandUpPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StandUpAppModel(context: Context) : BaseModel(context) {

    companion object {
        private var INSTANCE: StandUpAppModel? = null
        private var platform: String = ""
        private var clientVersion: String = ""
        private var language: String = ""
        private var deviceId: String = ""
        private var secureKey: String = ""
        private var deviceName: String = ""

        private lateinit var mContext: Context
        private lateinit var mStandUpShared: StandUpPreferenceManager

        fun getInstance(): StandUpAppModel {
            if (INSTANCE == null) {
                throw RuntimeException("JojoStore is being invoked before initializing")
            }
            return INSTANCE as StandUpAppModel
        }

        fun initStandUpAppModel(context: Context) {
            INSTANCE = StandUpAppModel(context)
            mContext = context
            mStandUpShared = StandUpPreferenceManager(mContext)
            getBaseParam()
        }

        @SuppressLint("HardwareIds")
        private fun getBaseParam() {
            val manager: PackageManager = mContext.packageManager
            val info = manager.getPackageInfo(mContext.packageName, PackageManager.GET_ACTIVITIES)
            try {
                val androidId = Settings.Secure.getString(
                    mContext.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
                deviceId = androidId
                deviceName = "${Build.MANUFACTURER} ${Build.MODEL}"
            } catch (e: Exception) {
                Log.e("DeviceIdException", e.localizedMessage ?: "")
            }
            platform = "android"
            language = "en"
            clientVersion = info.versionName
            secureKey = Constants.SECURE_KEY
        }
    }

    fun callDummyBreedList(): Disposable? {
        var response: List<BreedVO> = ArrayList(0)
        return mTheApi.callDummyBreedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    response = result
                    Log.d("ResourcesResponse", "Success Network Call")
                    Log.d("ResourcesResponse", "Breed list size is: " + response.size)
                    Toast.makeText(mContext, "Breed list size is: " + response.size, Toast.LENGTH_SHORT).show()

                    var counter = mStandUpShared.fromPreference("counter", "0").toLong()
                    counter++
                    mStandUpShared.toPreference(Constants.COUNTER, counter.toString())
                },
                { _ ->
                    val errorVO = ErrorVO(111, mContext.getString(R.string.server_error))
                    response = ArrayList(0)
                    Log.d("ResourcesResponse", "Server Error")
                    Toast.makeText(mContext, "Server Error", Toast.LENGTH_SHORT).show()

                    var counter = mStandUpShared.fromPreference(Constants.COUNTER, "0").toLong()
                    counter++
                    mStandUpShared.toPreference(Constants.COUNTER, counter.toString())
                })
    }
}