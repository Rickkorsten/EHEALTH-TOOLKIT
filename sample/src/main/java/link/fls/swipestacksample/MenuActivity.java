package link.fls.swipestacksample;

/**
 * Created by rickk on 31-5-2017.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView welkomTitle = (TextView) findViewById(R.id.welkomTitle);
        TextView random = (TextView) findViewById(R.id.buttonPerCategorie);
        TextView overzicht = (TextView) findViewById(R.id.buttonOverzicht);
        TextView geschiedenis = (TextView) findViewById(R.id.buttonGeschiedenis);
        TextView eigen = (TextView) findViewById(R.id.buttonEigenKaart);
        Typeface comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");
        Typeface comfortaaBold = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Bold.ttf");
        welkomTitle.setTypeface(comfortaa);
        random.setTypeface(comfortaaBold);
        overzicht.setTypeface(comfortaaBold);
        geschiedenis.setTypeface(comfortaaBold);
        eigen.setTypeface(comfortaaBold);


        goToKaartenOverzicht();
        goToCatagorieOverzicht();
        goToAddKaartOverzicht();
    }

    private void goToAddKaartOverzicht() {
        Button addKaartButton = (Button)findViewById(R.id.buttonEigenKaart);
        addKaartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, addcard.class));

            }


        });
    }

    private void goToKaartenOverzicht() {
        Button kaartenOverzichtButton = (Button)findViewById(R.id.buttonOverzicht);
        kaartenOverzichtButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this, MainActivity.class));

            }


        });
    }

    private void goToCatagorieOverzicht() {
        Button CatagorieButton = (Button)findViewById(R.id.buttonPerCategorie);
        CatagorieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CategorieActivity.class));
            }


        });
    }
}
