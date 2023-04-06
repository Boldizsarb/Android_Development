1. To explore when the various lifecycle methods are called, add `onStart()`, `onResume()`, `onStop()`, `onPause()` and `onDestroy()` to ***the main activity from last week's exercise***. In each method, as well as in `onCreate()`, display a `Log` message saying which method has been called. ***All these methods should call the superclass version of the method, e.g `super.onResume()`***. For example:You can display `Log` messages with the `logcat` tool. Select ***View-Tool Windows-Logcat*** to view log messages. A window below your code, similar to that shown below will appear:You need to add a ***filter*** to filter log messages to only those you are interested in, such as log messages with a particular ***log tag***. To do this, as shown on the diagram above, add a filter with a particular name, and in the filter settings, set it to filter only messages with a particular log tag (e.g. `lifecycle`).
    
    ```kotlin
    import android.util.Log
    
    // ...
    
    override fun onResume() {
        super.onResume()
        // First argument is log tag, second argument is message (see below)
        Log.d("lifecycle", "onResume")
    }
    
    ```
    
    ![https://nwcourses.github.io/images/logcat.png](https://nwcourses.github.io/images/logcat.png)
    
    Test by launching your app, changing the mapping provider using the second activity, returning to the main activity, and then pressing the Back button to quit the application entirely. **Note how many times the lifecycle methods are called.**
    
2. The idea of this exercise is to develop a version of your mapping app which uses preferences, rather than a custom activity. Add an extra menu item to launch the preferences, and add a preference activity containing a PreferenceFragment which includes the following:  Add code in the `onResume()` to get these working. Once this is working, add the following further preference:
    - Current Latitude and Longitude of the map (EditTextPreference; incorporate the code provided above)
    - Default zoom level for the map (EditTextPreference)
    - Map style - Regular or OpenTopoMap(use a ListPreference)