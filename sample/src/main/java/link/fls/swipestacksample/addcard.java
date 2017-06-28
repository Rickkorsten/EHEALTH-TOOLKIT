package link.fls.swipestacksample;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import models.TestModel;

public class addcard extends AppCompatActivity {

    EditText getdoel, getinzet, getonderwerp, titel, getuitvoering, geturl, getbronnen, getopdracht;
    TextView setcategorie, saveText;
    ButtonBarLayout submit;
    private TextView titleInhoud;
    private TextView titleInhoud2;
    private LinearLayout cardlayout;
    Integer selectedSubject;
    String url, categorie, inzet, setinzet;
    ScrollView addCardSroll;
    Window window;
    Button settime;

    ImageButton setUrlButton;

    private Typeface comfortaa;
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
        //getinzet = (EditText) findViewById(R.id.makeinzet);
        getonderwerp = (EditText) findViewById(R.id.titelinhoud);
        //gettitel = (EditText) findViewById(R.id.maketitel);
        getuitvoering = (EditText) findViewById(R.id.makeuitvoering);
        //geturl = (EditText) findViewById(R.id.makeurl);
        getbronnen = (EditText) findViewById(R.id.bronnen);
        getopdracht = (EditText) findViewById(R.id.opdracht);

        settime =  (Button) findViewById(R.id.settimebtn);

        addCardSroll = (ScrollView) findViewById(R.id.addcardscrollview);

        setcategorie = (TextView) findViewById(R.id.categorieinhoud);
        titleInhoud = (TextView) findViewById(R.id.titelinhoud);
        titleInhoud2 = (TextView) findViewById(R.id.titelinhoud2);
        cardlayout = (LinearLayout) findViewById(R.id.cardlayout);
        saveText = (TextView) findViewById(R.id.savetext);
        comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");
        titleInhoud.setTypeface(comfortaa);
        titleInhoud2.setTypeface(comfortaa);
        setcategorie.setTypeface(comfortaa);
        saveText.setTypeface(comfortaa);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }

        titleInhoud.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                titleInhoud2.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                titleInhoud2.setText(editable);
            }
        });

        submit = (ButtonBarLayout) findViewById(R.id.submitcard);
        setUrlButton = (ImageButton) findViewById(R.id.addUrlButton);

        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settimedialog();
            }
        });

        setUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Urldialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Agetdoel = getdoel.getText().toString();
                String Agetonderwerp = getonderwerp.getText().toString();
                String Agetuitvoering = getuitvoering.getText().toString();
                String Agetbronnen = getbronnen.getText().toString();
                String Agetopdracht = getopdracht.getText().toString();

                Boolean truefalse = true;

                // check if one of the fields in empty

                if (Agetdoel.matches("")) {
                    truefalse = false;
                }
                if (Agetonderwerp.matches("")) {
                    truefalse = false;
                }
                if (Agetuitvoering.matches("")) {
                    truefalse = false;
                }
                if (Agetbronnen.matches("")) {
                    truefalse = false;
                }
                if (Agetopdracht.matches("")) {
                    truefalse = false;
                }

                if (!truefalse) {
                    Toast.makeText(getApplicationContext(), "Een of meerdere velden zijn niet ingevuld", Toast.LENGTH_SHORT).show();
                } else {

                    String id = databasekaarten.push().getKey();
                    TestModel testmodel = new TestModel(Agetdoel, setinzet, Agetonderwerp, categorie, Agetuitvoering, url, Agetbronnen, Agetopdracht);

                    databasekaarten.child("Customcards").child(id).setValue(testmodel);

                    Toast.makeText(getApplicationContext(), Agetonderwerp + " is aangemaakt", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }

    private void Urldialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("set URL");
        // Get the layout inflater
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        // Add action buttons
        builder.setPositiveButton("accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                url = input.getText().toString();
                Toast.makeText(getBaseContext(), url, Toast.LENGTH_LONG).show();
                ImageView image = (ImageView) findViewById(R.id.cardImage);
                Picasso.with(getBaseContext()).load(url).into(image);
            }
        });
        AlertDialog chooseSubject = builder.create();
        chooseSubject.show();
    }

    public void setdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtitle)
                .setItems(R.array.subjectArray2, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String[] sexArray = getResources().getStringArray(R.array.subjectArray2);
                        setcategorie.setText(sexArray[which]);
                        categorie = sexArray[which];
                        if (sexArray[which].equals("Prototype")) {
                            titleInhoud.setBackgroundColor(getColor(R.color.green));
                            titleInhoud2.setBackgroundColor(getColor(R.color.green));
                            addCardSroll.setBackgroundColor(getColor(R.color.greenL));
                            window.setStatusBarColor(getColor(R.color.green));
                        }
                        if (sexArray[which].equals("Concepting")) {
                            titleInhoud.setBackgroundColor(getColor(R.color.blue));
                            titleInhoud2.setBackgroundColor(getColor(R.color.blue));
                            addCardSroll.setBackgroundColor(getColor(R.color.blueL));
                            window.setStatusBarColor(getColor(R.color.blue));
                        }
                        if (sexArray[which].equals("Probleem")) {
                            titleInhoud.setBackgroundColor(getColor(R.color.magenta));
                            titleInhoud2.setBackgroundColor(getColor(R.color.magenta));
                            addCardSroll.setBackgroundColor(getColor(R.color.magentaL));
                            window.setStatusBarColor(getColor(R.color.magenta));
                        }
                        if (sexArray[which].equals("Idee")) {
                            titleInhoud.setBackgroundColor(getColor(R.color.yellow));
                            titleInhoud2.setBackgroundColor(getColor(R.color.yellow));
                            addCardSroll.setBackgroundColor(getColor(R.color.yellowL));
                            window.setStatusBarColor(getColor(R.color.yellow));
                        }

                    }
                });

        AlertDialog chooseSubject = builder.create();
        chooseSubject.setCancelable(false);
        chooseSubject.setCanceledOnTouchOutside(false);
        chooseSubject.show();

    }

    public void settimedialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set time")
                .setItems(R.array.timeArray, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String[] sexArray = getResources().getStringArray(R.array.timeArray);
                        inzet = sexArray[which];

                        if (inzet.equals("1 uur")){
                            setinzet = "1";
                        }
                        if (inzet.equals("2 uur")){
                            setinzet = "2";
                        }
                        if (inzet.equals("3 uur")){
                            setinzet = "3";
                        }
                        if (inzet.equals("1 dag")){
                            setinzet = "4";
                        }
                        if (inzet.equals("2 dag")){
                            setinzet = "5";
                        }
                        if (inzet.equals("3 dag")){
                            setinzet = "6";
                        }
                        if (inzet.equals("1 week")){
                            setinzet = "7";
                        }


                    }
                });
        AlertDialog chooseSubject = builder.create();
        chooseSubject.show();

    }
}

