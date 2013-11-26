package nl.droidcon.myriver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Write comment here.
 * <p/>
 * Creator: <lukasz.izmajlowicz@mydriver.com>
 * Date: 24/11/13
 */
public class ResultActivity extends Activity {
    private ImageButton mResultButton;

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
            final MainActivity.Mode mode = extras.getParcelable("mode");
            showResultImage(mode);
        }
    }

    private void showResultImage(MainActivity.Mode mode) {
        mResultButton.setBackgroundResource(mode.background());
        mResultButton.setImageResource(mode.picture());
    }
}
