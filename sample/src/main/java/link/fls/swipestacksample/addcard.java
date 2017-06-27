package link.fls.swipestacksample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        setcategorie = (TextView)findViewById(R.id.categorieinhoud);

        submit = (Button) findViewById(R.id.submitcard);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Agetdoel = getdoel.getText().toString();
                String Agetinzet = getinzet.getText().toString();
                String Agetonderwerp = getonderwerp.getText().toString();
                String Agettitel = gettitel.getText().toString();
                String Agetuitvoering = getuitvoering.getText().toString();
                String Ageturl = geturl.getText().toString();

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
                if (Agettitel.matches("")) {
                    truefalse = false;
                }
                if (Agetuitvoering.matches("")) {
                    truefalse = false;
                }
                if (Ageturl.matches("")) {
                    truefalse = false;
                }

                if (!truefalse) {
                    Toast.makeText(getApplicationContext(), "Een of meerdere velden zijn niet ingevuld", Toast.LENGTH_SHORT).show();
                } else {

                    String id = databasekaarten.push().getKey();
                    TestModel testmodel = new TestModel(Agetdoel, Agetinzet, Agetonderwerp, Agettitel, Agetuitvoering, Ageturl);

                    databasekaarten.child(id).setValue(testmodel);

                    Toast.makeText(getApplicationContext(), "kaart uploaded to database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtitle)
                .setItems(R.array.subjectArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// The 'which' argument contains the index position
// of the selected item
                        String[] sexArray =  getResources().getStringArray(R.array.subjectArray);
                        setcategorie.setText(sexArray[which]);

                    }
                });
        AlertDialog chooseSubject = builder.create();
        chooseSubject.show();

    }
}

