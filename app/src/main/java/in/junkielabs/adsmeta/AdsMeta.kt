package `in`.junkielabs.adsmeta

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.the3deer.util.android.AndroidURLStreamHandlerFactory
import java.net.URL


// commments here
@HiltAndroidApp
class AdsMeta :Application(){

    override fun onCreate() {
        super.onCreate()
        System.setProperty(
            "java.protocol.handler.pkgs",
            packageName
        ); URL.setURLStreamHandlerFactory(AndroidURLStreamHandlerFactory ());
    }

}