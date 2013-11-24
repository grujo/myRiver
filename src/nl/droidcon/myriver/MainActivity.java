package nl.droidcon.myriver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import it.imwatch.SimpleShakeDetector;

public class MainActivity extends Activity implements SimpleShakeDetector.OnShakeListener {
    private SimpleShakeDetector simpleShakeDetector;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        simpleShakeDetector = new SimpleShakeDetector(this, this, 100);
        simpleShakeDetector.setMinGestureSize(1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        simpleShakeDetector.onResume();
    }

    @Override
    protected void onPause() {
        simpleShakeDetector.onPause();
        super.onPause();
    }

    private int shakedTimes = 0;

    @Override
    public void onShakeDetected(int i) {
        if (shakedTimes++ >= 3) {
            Log.i("MR", "Rock!");
            shakedTimes = 0;
        }
    }
}
