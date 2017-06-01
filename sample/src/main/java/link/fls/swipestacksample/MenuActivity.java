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

public class MenuActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView welkomTitle = (TextView) findViewById(R.id.welkomTitle);
        Typeface comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");
        welkomTitle.setTypeface(comfortaa);

        goToKaartenOverzicht();
        goToCatagorieOverzicht();
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
