package link.fls.swipestacksample;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class CardViewActivity extends AppCompatActivity {

    boolean isBackShow;
    ImageButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        getFragmentManager().beginTransaction().replace(R.id.container, new CardFrontFragment()).commit();


        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        switchclick();
    }

    public void onFlipClick(){
        if (isBackShow){
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in,R.animator.card_flip_right_out)
                    .replace(R.id.container, new CardFrontFragment()).commit();
            isBackShow =false;

        }else{
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_left_in,R.animator.card_flip_left_out)
                    .replace(R.id.container, new CardBackFragment()).commit();
            isBackShow =true;
        }
    }

    private void switchclick(){
        switchButton = (ImageButton)findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFlipClick();
            }
        });
    }

    static public class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_back, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    static public class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_front, container, false);
        }
    }

}
