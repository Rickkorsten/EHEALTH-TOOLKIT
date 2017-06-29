package link.fls.swipestacksample;

/**
 * Created by rickk on 31-5-2017.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity  {


    @RequiresApi(api = Build.VERSION_CODES.M)
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.blueL));
        }

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

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("CUSTOM", "FALSE");
                startActivity(intent);

            }


        });
    }

    private void goToCatagorieOverzicht() {
        Button CatagorieButton = (Button)findViewById(R.id.buttonPerCategorie);
        CatagorieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("CUSTOM", "TRUE");
                startActivity(intent);
            }


        });
    }
}
