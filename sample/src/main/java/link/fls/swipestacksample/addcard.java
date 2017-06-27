package link.fls.swipestacksample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.TestModel;

public class addcard extends AppCompatActivity {

    EditText getdoel, getinzet, getonderwerp, gettitel, getuitvoering, geturl;
    TextView setcategorie;
    Button submit;
    Integer selectedSubject;
    String url, categorie;
    ImageButton setUrlButton;

    DatabaseReference databasekaarten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        // show dialog
        setdialog();

        // get from firebase
        databasekaarten = FirebaseDatabase.getInstance().getReference("Cards");

        getdoel = (EditText) findViewById(R.id.makedoel);
        getinzet = (EditText) findViewById(R.id.makeinzet);
        getonderwerp = (EditText) findViewById(R.id.makeonderwerp);
        gettitel = (EditText) findViewById(R.id.maketitel);
        getuitvoering = (EditText) findViewById(R.id.makeuitvoering);
        geturl = (EditText) findViewById(R.id.makeurl);

        setcategorie = (TextView) findViewById(R.id.categorieinhoud);

        submit = (Button) findViewById(R.id.submitcard);
        setUrlButton = (ImageButton) findViewById(R.id.addUrlButton);

        setUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Urldialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Agetdoel = getdoel.getText().toString();
                String Agetinzet = getinzet.getText().toString();
                String Agetonderwerp = getonderwerp.getText().toString();
                String Agetuitvoering = getuitvoering.getText().toString();


                Boolean truefalse = true;

                // check if one of the fields in empty

                if (Agetdoel.matches("")) {
                    truefalse = false;
                }
                if (Agetinzet.matches("")) {
                    truefalse = false;
                }
                if (Agetonderwerp.matches("")) {
                    truefalse = false;
                }

                if (Agetuitvoering.matches("")) {
                    truefalse = false;
                }
                if (url.matches("")) {
                    truefalse = false;
                }

                if (!truefalse) {
                    Toast.makeText(getApplicationContext(), "Een of meerdere velden zijn niet ingevuld", Toast.LENGTH_SHORT).show();
                } else {

                    String id = databasekaarten.push().getKey();
                    TestModel testmodel = new TestModel(Agetdoel, Agetinzet, Agetonderwerp, categorie, Agetuitvoering, url);

                    databasekaarten.child(id).setValue(testmodel);

                    Toast.makeText(getApplicationContext(), "kaart uploaded to database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Urldialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

// Inflate and set the layout for the dialog
// Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialogurl, null))
// Add action buttons
                .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        EditText geturl = (EditText)findViewById(R.id.url);
                        url = geturl.getText().toString();
                    }
                });
        AlertDialog chooseSubject = builder.create();
        chooseSubject.show();
    }

    public void setdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtitle)
                .setItems(R.array.subjectArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// The 'which' argument contains the index position
// of the selected item
                        String[] sexArray = getResources().getStringArray(R.array.subjectArray);
                        setcategorie.setText(sexArray[which]);
                        categorie = sexArray[which];

                    }
                });
        AlertDialog chooseSubject = builder.create();
        chooseSubject.show();

    }
}

