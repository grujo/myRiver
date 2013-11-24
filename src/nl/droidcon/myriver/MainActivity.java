package nl.droidcon.myriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import it.imwatch.SimpleShakeDetector;

public class MainActivity extends Activity {


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
    }

    public void onPaperSelected(View view) {
        mSelectedMode = Mode.PAPER;
        showDetector();
    }

    public void onRockSelected(View view) {
        mSelectedMode = Mode.ROCK;
        showDetector();
    }

    public void onScisorsSelected(View view) {
        mSelectedMode = Mode.SCISORS;
        showDetector();
    }

    public void onRandSelected(View view) {
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

    private void bindViews() {
        mPaper = (ImageButton) findViewById(R.id.paper);
        mRock = (ImageButton) findViewById(R.id.paper);
        mScrisors = (ImageButton) findViewById(R.id.paper);
        mRandom = (ImageButton) findViewById(R.id.paper);

    }

    private ImageButton mPaper;
    private ImageButton mRock;
    private ImageButton mScrisors;
    private ImageButton mRandom;
}
