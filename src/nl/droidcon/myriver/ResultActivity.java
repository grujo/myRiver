package nl.droidcon.myriver;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

/**
 * Write comment here.
 * <p/>
 * Creator: <lukasz.izmajlowicz@mydriver.com>
 * Date: 24/11/13
 */
public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        mResultButton = (ImageButton) findViewById(R.id.result);
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Bundle extras = getIntent().getExtras();
        if (getIntent().hasExtra("mode")) {
            final Parcelable mode = extras.getParcelable("mode");
            showResultImage(mode);
        }
    }

    private void showResultImage(Parcelable mode) {
        if (mode.equals(MainActivity.Mode.PAPER)) {
            mResultButton.setImageDrawable(getResources().getDrawable(R.drawable.paper));
        } else if (mode.equals(MainActivity.Mode.ROCK)) {
            mResultButton.setImageDrawable(getResources().getDrawable(R.drawable.rock));
        } else {
            mResultButton.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
        }
    }

    ImageButton mResultButton;
}
