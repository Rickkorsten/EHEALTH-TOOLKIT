package link.fls.swipestacksample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class CardViewActivity extends AppCompatActivity {

    boolean isBackShow;
    ImageButton switchButton;
    private Typeface comfortaa;
    public String onderwerp;
    public String title;
    public String doel;
    public String uitkomst;
    public String inzet;
    public String url;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        onderwerp = getIntent().getStringExtra("ONDERWERP");
        title = getIntent().getStringExtra("TITLE");
        doel = getIntent().getStringExtra("DOEL");
        uitkomst = getIntent().getStringExtra("UITKOMST");
        inzet = getIntent().getStringExtra("INZET");
        url = getIntent().getStringExtra("URL");

        Toast.makeText(getBaseContext(), onderwerp, Toast.LENGTH_LONG).show();

        comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");

        getFragmentManager().beginTransaction().replace(R.id.container, new CardFrontFragment()).commit();

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        switchclick();
    }

    public void onFlipClick() {
        if (isBackShow) {
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out)
                    .replace(R.id.container, new CardFrontFragment()).commit();
            isBackShow = false;

        } else {
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.container, new CardBackFragment()).commit();
            isBackShow = true;
        }
    }

    private void switchclick() {
        switchButton = (ImageButton) findViewById(R.id.switchButton);
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

            View V = inflater.inflate(R.layout.card_back, container, false);

            String doel = getActivity().getIntent().getStringExtra("DOEL");

            // get doel
            TextView textViewDoel = (TextView) V.findViewById(R.id.doelinhoud);
            textViewDoel.setText(doel);

            // get inzet
//            TextView textViewInzet = (TextView) findViewById(R.id.inzetinhoud);
//            textViewInzet.setText(inzet);
//            // get onderwerp
//            final TextView textViewOnderwerp = (TextView) findViewById(R.id.titelinhoud);
//            textViewOnderwerp.setTypeface(comfortaa);
//            textViewOnderwerp.setText(onderwerp);
//            // get titel
//            TextView textViewTitel = (TextView) findViewById(R.id.categorieinhoud);
//            RelativeLayout kaartcontent = (RelativeLayout) findViewById(R.id.kaartcontent);
//            textViewTitel.setTypeface(comfortaa);
//            textViewTitel.setText(title);
//            if (title.equals("Probleem")) {
//                kaartcontent.setBackgroundColor(getColor(R.color.magentaL));
//                textViewOnderwerp.setBackgroundColor(getColor(R.color.magenta));
//            } else if (title.equals("Concepting")) {
//                kaartcontent.setBackgroundColor(getColor(R.color.blueL));
//                textViewOnderwerp.setBackgroundColor(getColor(R.color.blue));
//            } else if (title.equals("Prototype")) {
//                kaartcontent.setBackgroundColor(getColor(R.color.greenL));
//                textViewOnderwerp.setBackgroundColor(getColor(R.color.green));
//            }
//            // get uitvoering
//            TextView textViewUitvoering = (TextView) findViewById(R.id.uitvoeringinhoud);
//            textViewUitvoering.setText(uitkomst);
//            // get URL
//            ImageView image = (ImageView) findViewById(R.id.cardImage);
//            Picasso.with(getBaseContext()).load(url).into(image);

            return V;
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
