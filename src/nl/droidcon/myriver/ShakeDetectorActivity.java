package nl.droidcon.myriver;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import it.imwatch.SimpleShakeDetector;
import nl.droidcon.myriver.logic.SimpleAI;

/**
 * Write comment here.
 * <p/>
 * Creator: <lukasz.izmajlowicz@mydriver.com>
 * Date: 24/11/13
 */
public class ShakeDetectorActivity extends Activity implements SimpleShakeDetector.OnShakeListener {
    private SimpleShakeDetector simpleShakeDetector;
    private TextView counterView;
    private MainActivity.Mode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake);

        simpleShakeDetector = new SimpleShakeDetector(this, this, SimpleShakeDetector.DEFAULT_UPDATE_INTERVAL);
        simpleShakeDetector.setMinGestureSize(1);

        counterView = (TextView) findViewById(R.id.counter);

        counterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shakedTimes = 0;
                updateCounterInUi();
                Log.i("MR", "Reset to 0");
            }
        });

        final Bundle extras = getIntent().getExtras();
        if (getIntent().hasExtra("mode")) {
            mMode = extras.getParcelable("mode");
//            toast(mMode.name());
        }

        updateCounterInUi();
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
        Log.i("MR", "" + shakedTimes);
        updateCounterInUi();

        if (shakedTimes++ >= 2) {
            Log.i("MR", "Rock!");

            toast(mMode.name() + " vs " + SimpleAI.runAI().name());

            shakedTimes = 0;
        }
    }

    private void updateCounterInUi() {
        counterView.setText(String.valueOf(shakedTimes));
    }

    private void toast(String log) {
        Toast.makeText(this, log, 0).show();
    }

    private View resetView;
}
