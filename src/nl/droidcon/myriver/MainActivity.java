package nl.droidcon.myriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import it.imwatch.SimpleShakeDetector;

public class MainActivity extends Activity implements SimpleShakeDetector.OnShakeListener {
    private SimpleShakeDetector simpleShakeDetector;


    private Mode mSelectedMode = Mode.RAND;

    public enum Mode implements Parcelable {
        PAPER,
        ROCK,
        SCISORS,
        RAND;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeInt(ordinal());
        }

        public static final Creator<Mode> CREATOR = new Parcelable.Creator<Mode>() {
            @Override
            public Mode createFromParcel(final Parcel source) {
                return Mode.values()[source.readInt()];
            }

            @Override
            public Mode[] newArray(final int size) {
                return new Mode[size];
            }
        };
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindViews();

        simpleShakeDetector = new SimpleShakeDetector(this, this, SimpleShakeDetector.DEFAULT_UPDATE_INTERVAL);
        simpleShakeDetector.setMinGestureSize(1);


        resetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shakedTimes = 0;
                Log.i("MR", "Reset to 0");
            }
        });
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

        if (shakedTimes++ >= 2) {
            Log.i("MR", "Rock!");
            shakedTimes = 0;
        }
    }

    public void onPaperSelected(View view) {
        toast("paper");
        mSelectedMode = Mode.PAPER;
        showDetector();
    }

    public void onRockSelected(View view) {
        toast("rock");
        mSelectedMode = Mode.ROCK;
        showDetector();
    }

    public void onScisorsSelected(View view) {
        toast("scisors");
        mSelectedMode = Mode.SCISORS;
        showDetector();
    }

    public void onRandSelected(View view) {
        toast("random");
        mSelectedMode = Mode.RAND;
        showDetector();
    }

    private void showDetector() {
        Bundle extras = new Bundle();
        extras.putParcelable("mode", mSelectedMode);

        Intent intent = new Intent(MainActivity.this, ShakeDetectorActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void toast(String log) {
        Toast.makeText(this, log, 0).show();
    }

    private void bindViews() {
        mPaper = (Button) findViewById(R.id.paper);
        mRock = (Button) findViewById(R.id.paper);
        mScrisors = (Button) findViewById(R.id.paper);
        mRandom = (Button) findViewById(R.id.paper);
        resetView = findViewById(R.id.reset);
    }

    private Button mPaper;
    private Button mRock;
    private Button mScrisors;
    private Button mRandom;
    private View resetView;
}
