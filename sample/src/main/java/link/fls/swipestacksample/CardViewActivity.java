package link.fls.swipestacksample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class CardViewActivity extends AppCompatActivity {

    boolean isBackShow;
    private ButtonBarLayout switchButton;
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

        LinearLayout layout = (LinearLayout) findViewById(R.id.cardActivity);

        if (title.equals("Probleem")) {
            layout.setBackgroundColor(getColor(R.color.magentaL));
        } else if (title.equals("Concepting")) {
            layout.setBackgroundColor(getColor(R.color.blueL));
        } else if (title.equals("Prototype")) {
            layout.setBackgroundColor(getColor(R.color.greenL));
        } else if (title.equals("Idee")) {
            layout.setBackgroundColor(getColor(R.color.yellowL));
        }

        comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");

        TextView flipText = (TextView) findViewById(R.id.textView4);
        flipText.setTypeface(comfortaa);

        getFragmentManager().beginTransaction().replace(R.id.container, new CardFrontFragment()).commit();

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (title.equals("Probleem")) {
                window.setStatusBarColor(getColor(R.color.magentaL));
            } else if (title.equals("Concepting")) {
                window.setStatusBarColor(getColor(R.color.blueL));
            } else if (title.equals("Prototype")) {
                window.setStatusBarColor(getColor(R.color.greenL));
            } else if (title.equals("Idee")) {
                window.setStatusBarColor(getColor(R.color.yellowL));
            }
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
        switchButton = (ButtonBarLayout) findViewById(R.id.switchButton);
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
            String onderwerp =  getActivity().getIntent().getStringExtra("ONDERWERP");
            String title =  getActivity().getIntent().getStringExtra("TITLE");
            String uitkomst =  getActivity().getIntent().getStringExtra("UITKOMST");
            String inzet =  getActivity().getIntent().getStringExtra("INZET");
            String url =  getActivity().getIntent().getStringExtra("URL");


            Typeface comfortaa = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Comfortaa-Regular.ttf");

            // get doel
            TextView textViewDoel = (TextView) V.findViewById(R.id.doelinhoud);
            textViewDoel.setText(doel);

            // get inzet
            TextView textViewInzet = (TextView) V.findViewById(R.id.inzetinhoud);
            String setinzet = "";

            if (inzet.equals("1")){
                setinzet = "1 uur";
            }
            if (inzet.equals("2")){
                setinzet = "2 uur";
            }
            if (inzet.equals("3")){
                setinzet = "3 uur";
            }
            if (inzet.equals("4")){
                setinzet = "1 dag";
            }
            if (inzet.equals("5")){
                setinzet = "2 dagen";
            }
            if (inzet.equals("6")){
                setinzet = "3 dagen";
            }
            if (inzet.equals("7")){
                setinzet = "1 week";
            }

            textViewInzet.setText(setinzet);

            // get onderwerp
            final TextView textViewOnderwerp = (TextView) V.findViewById(R.id.titelinhoud);
            textViewOnderwerp.setTypeface(comfortaa);
            textViewOnderwerp.setText(onderwerp);

            // get titel
            TextView textViewTitel = (TextView) V.findViewById(R.id.categorieinhoud);
            RelativeLayout kaartcontent = (RelativeLayout) V.findViewById(R.id.kaartcontent);
            textViewTitel.setTypeface(comfortaa);
            textViewTitel.setText(title);

            if (title.equals("Probleem")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.magentaL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.magenta));
            } else if (title.equals("Concepting")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.blueL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            } else if (title.equals("Prototype")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.greenL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
            } else if (title.equals("Idee")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.greenL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
            }
            // get uitvoering
            TextView textViewUitvoering = (TextView) V.findViewById(R.id.uitvoeringinhoud);
            textViewUitvoering.setText(uitkomst);
            // get URL
            ImageView image = (ImageView) V.findViewById(R.id.cardImage);
            if (url != null)
                Picasso.with(getActivity().getBaseContext()).load(url).into(image);
            else
                Picasso.with(getActivity().getBaseContext()).load("http://www.colorhexa.com/333333.png").into(image);

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

            View V = inflater.inflate(R.layout.card_front, container, false);

            String onderwerp =  getActivity().getIntent().getStringExtra("ONDERWERP");
            String title =  getActivity().getIntent().getStringExtra("TITLE");
            String bronnen =  getActivity().getIntent().getStringExtra("BRONNEN");
            String opdracht =  getActivity().getIntent().getStringExtra("OPDRACHT");

            Typeface comfortaa = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Comfortaa-Regular.ttf");

            TextView textViewBronnen = (TextView) V.findViewById(R.id.bronneninhoud);
            textViewBronnen.setText(bronnen);

            TextView textViewInhoud = (TextView) V.findViewById(R.id.opdrachtinhoud);
            textViewInhoud.setText(opdracht);

            // get onderwerp
            final TextView textViewOnderwerp = (TextView) V.findViewById(R.id.titelinhoud);
            textViewOnderwerp.setTypeface(comfortaa);
            textViewOnderwerp.setText(onderwerp);

            if (title.equals("Probleem")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.magentaL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.magenta));
            } else if (title.equals("Concepting")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.blueL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            } else if (title.equals("Prototype")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.greenL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
            } else if (title.equals("Idee")) {
                //kaartcontent.setBackgroundColor(getActivity().getResources().getColor(R.color.greenL));
                textViewOnderwerp.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
            }
            return V;
        }
    }

}
