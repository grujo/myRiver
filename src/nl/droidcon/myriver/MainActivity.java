package nl.droidcon.myriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View paper = findViewById(R.id.paper);
        paper.setTag(Mode.PAPER);
        paper.setOnClickListener(this);

        View rock = findViewById(R.id.rock);
        rock.setTag(Mode.ROCK);
        rock.setOnClickListener(this);

        View scissors = findViewById(R.id.scissors);
        scissors.setTag(Mode.SCISSORS);
        scissors.setOnClickListener(this);

        View random = findViewById(R.id.rand);
        random.setTag(Mode.RAND);
        random.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Mode mode= (Mode) v.getTag();
        showDetector(mode);
    }

    private void showDetector(Mode mode) {
        Bundle extras = new Bundle();
        extras.putParcelable("mode", mode);

        Intent intent = new Intent(this, ShakeDetectorActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public enum Mode implements Parcelable {
        PAPER(R.drawable.paper, R.drawable.selector_paper),
        ROCK(R.drawable.rock, R.drawable.selector_rock),
        SCISSORS(R.drawable.scissors, R.drawable.selector_scissors),
        RAND(R.drawable.random, R.drawable.selector_random);
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
        private final int background;
        private final int picture;

        Mode(int picture, int background) {
            this.picture = picture;
            this.background = background;
        }

        public int background() {
            return background;
        }

        public int picture() {
            return picture;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeInt(ordinal());
        }
    }
}
