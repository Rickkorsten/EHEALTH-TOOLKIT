package link.fls.swipestacksample;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;





public class CardViewActivity extends AppCompatActivity {

    boolean isBackShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CardFrontFragment()).commit();
    }

    public void onFlipClick(View view) {
        if (isBackShow) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_in)
                    .replace(R.id.container, new CardFrontFragment()).commit();
            isBackShow = false;
        }else{
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_in)
                    .replace(R.id.container, new CardBackFragment()).commit();
            isBackShow = true;
        }
    }




    static public class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_front, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    static public class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_back, container, false);
        }
    }

}
