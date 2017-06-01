package link.fls.swipestacksample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class CategorieActivity extends AppCompatActivity {

    CardView idee;
    CardView prototpye;
    CardView probleem;
    CardView concept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
    }

    private void onclickcategory(){
        idee = (CardView) findViewById(R.id.cardView);
        idee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CategorieActivity.this, MainActivity.class));
            }


        });

        prototpye = (CardView) findViewById(R.id.cardView3);
        prototpye.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CategorieActivity.this, MainActivity.class));
            }


        });

        probleem = (CardView) findViewById(R.id.cardView4);
        probleem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CategorieActivity.this, MainActivity.class));
            }


        });

        concept = (CardView) findViewById(R.id.cardView2);
        concept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CategorieActivity.this, MainActivity.class));
            }


        });
    }
}
