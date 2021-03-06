/*
 * Copyright (C) 2016 Frederik Schweiger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package link.fls.swipestacksample;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import link.fls.swipestack.SwipeStack;
import models.TestModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, View.OnClickListener {

    private Button mButtonLeft, mButtonRight, sortbtn, categoriebtn;
    private ButtonBarLayout mFab;
    private CardView cardviewcard;
    private ArrayList<TestModel> mData = new ArrayList<>();
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    private int position;
    private String value, fotoURL, sortvalue, querySave;
    private Typeface comfortaa;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriebtn = (Button) findViewById(R.id.categorieKies);
        sortbtn = (Button) findViewById(R.id.sort);

        if (getIntent().getStringExtra("CUSTOM").equals("TRUE")){
            querySave = "Customcards";
            categoriebtn.setVisibility(View.INVISIBLE);
            sortbtn.setBackgroundColor(getColor(R.color.magentaL));
            findViewById(R.id.topButtons).setBackgroundColor(getColor(R.color.magentaL));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getColor(R.color.magentaL));
            }
        } else {
            querySave = "Concepting";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getColor(R.color.blueL));
            }
        }

        // get from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // query
        Query ref = database.getReference("Cards").child(querySave);

        // parse ref into card content loader
        getFirebaseContent(ref);

        //load views
        mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack);

        // set clicks
        sortbtn.setOnClickListener(this);
        categoriebtn.setOnClickListener(this);
        // set adapter
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);
        comfortaa = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");

        sortbtn.setTypeface(comfortaa);
        categoriebtn.setTypeface(comfortaa);


    }

    public void getFirebaseContent(Query ref) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot
                    final HashMap<String, Object> row = (HashMap<String, Object>) postSnapshot.getValue();
                    // mData.add(new TestModel((String )row.get("doel"), (String) row.get("inzet"),(String)row.get("onderwerp"),(String)row.get("titel"),(String)row.get("uitvoering")));
                    mData.add((TestModel) postSnapshot.getValue(TestModel.class));
                    mAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mFab)) {


            //  startActivity(new Intent(MainActivity.this, CardViewActivity.class));
            mAdapter.notifyDataSetChanged();
        }

        if (v.equals(sortbtn)) {
            setsortdialog();

        }

        if (v.equals(categoriebtn)) {
            setcategoriedialog();

        }

    }

    public void setsortdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sorteren op:")
                .setItems(R.array.sortArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// The 'which' argument contains the index position
// of the selected item
                        String[] sexArray = getResources().getStringArray(R.array.sortArray);
                        sortvalue = sexArray[which];

                        if (sortvalue.equals("Tijd")) {

                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child(querySave).orderByChild("inzet");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();

                        }
                        if (sortvalue.equals("Titel")) {

                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child(querySave).orderByChild("onderwerp");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                        }
                    }


                });
        AlertDialog sortdialog = builder.create();
        sortdialog.show();

    }

    public void setcategoriedialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kies een categorie:")
                .setItems(R.array.subjectArray, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog, int which) {
// The 'which' argument contains the index position
// of the selected item
                        String[] sexArray = getResources().getStringArray(R.array.subjectArray);
                        sortvalue = sexArray[which];

                        if (sortvalue.equals("Probleem")) {
                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child("Probleem");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                            querySave = "Probleem";
                            categoriebtn.setBackgroundColor(getColor(R.color.magentaL));
                            sortbtn.setBackgroundColor(getColor(R.color.magentaL));
                            findViewById(R.id.topButtons).setBackgroundColor(getColor(R.color.magentaL));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(getColor(R.color.magentaL));
                            }
                        }
                        if (sortvalue.equals("Idee")) {
                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child("Idee");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                            querySave = "Idee";
                            categoriebtn.setBackgroundColor(getColor(R.color.yellowL));
                            sortbtn.setBackgroundColor(getColor(R.color.yellowL));
                            findViewById(R.id.topButtons).setBackgroundColor(getColor(R.color.yellowL));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(getColor(R.color.yellowL));
                            }
                        }
                        if (sortvalue.equals("Prototype")) {
                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child("Prototype");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                            querySave = "Prototype";
                            categoriebtn.setBackgroundColor(getColor(R.color.greenL));
                            sortbtn.setBackgroundColor(getColor(R.color.greenL));
                            findViewById(R.id.topButtons).setBackgroundColor(getColor(R.color.greenL));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(getColor(R.color.greenL));
                            }
                        }
                        if (sortvalue.equals("Concepting")) {
                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child("Concepting");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                            querySave = "Concepting";
                            categoriebtn.setBackgroundColor(getColor(R.color.blueL));
                            sortbtn.setBackgroundColor(getColor(R.color.blueL));
                            findViewById(R.id.topButtons).setBackgroundColor(getColor(R.color.blueL));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(getColor(R.color.blueL));
                            }
                        }
                        if (sortvalue.equals("Customcards")) {
                            // clear the array
                            mData.clear();
                            // firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // query
                            Query ref = database.getReference("Cards").child("Customcards");
                            getFirebaseContent(ref);
                            mAdapter.notifyDataSetChanged();
                            querySave = "Customcards";
                        }

                    }
                });
        AlertDialog sortdialog = builder.create();
        sortdialog.show();

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onStackEmpty() {
        // clear the array
        mData.clear();
        // firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // query
        Query ref = database.getReference("Cards").child(querySave);
        getFirebaseContent(ref);
        mAdapter.notifyDataSetChanged();
    }

    //////////// SWIPE STACKADAPTER CLASS //////////////////
    public class SwipeStackAdapter extends BaseAdapter {

        private List<TestModel> mData;

        public SwipeStackAdapter(List<TestModel> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public TestModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            }

            // get doel
            TextView textViewDoel = (TextView) convertView.findViewById(R.id.doelinhoud);
            textViewDoel.setText(mData.get(position).getDoel());
            // get inzet
            TextView textViewInzet = (TextView) convertView.findViewById(R.id.inzetinhoud);
            String test;
            String inzet = mData.get(position).getInzet();

            if (inzet.equals("1")){
                test = "1 uur";
            }
            else if (inzet.equals("2")){
                test = "2 uur";
            }
            else if (inzet.equals("3")){
                test = "3 uur";
            }
            else if (inzet.equals("4")){
                test = "1 dag";
            }
            else if (inzet.equals("5")){
                test = "2 dagen";
            }
            else if (inzet.equals("6")){
                test = "3 dagen";
            }
            else if (inzet.equals("7")){
                test = "1 week";
            } else {
                test = "None";
            }

            textViewInzet.setText(test);
            // get onderwerp
            final TextView textViewOnderwerp = (TextView) convertView.findViewById(R.id.titelinhoud);
            textViewOnderwerp.setTypeface(comfortaa);
            textViewOnderwerp.setText(mData.get(position).getOnderwerp());
            // get titel
            String title = mData.get(position).getTitel();
            TextView textViewTitel = (TextView) convertView.findViewById(R.id.categorieinhoud);
            RelativeLayout kaartcontent = (RelativeLayout) convertView.findViewById(R.id.kaartcontent);
            textViewTitel.setTypeface(comfortaa);
            textViewTitel.setText(title);
            if (title.equals("Probleem")) {
                kaartcontent.setBackgroundColor(getColor(R.color.magentaL));
                textViewOnderwerp.setBackgroundColor(getColor(R.color.magenta));
            } else if (title.equals("Concepting")) {
                kaartcontent.setBackgroundColor(getColor(R.color.blueL));
                textViewOnderwerp.setBackgroundColor(getColor(R.color.blue));
            } else if (title.equals("Prototype")) {
                kaartcontent.setBackgroundColor(getColor(R.color.greenL));
                textViewOnderwerp.setBackgroundColor(getColor(R.color.green));
            } else if (title.equals("Idee")) {
                kaartcontent.setBackgroundColor(getColor(R.color.yellowL));
                textViewOnderwerp.setBackgroundColor(getColor(R.color.yellow));
            }
            // get uitvoering
            TextView textViewUitvoering = (TextView) convertView.findViewById(R.id.uitvoeringinhoud);
            textViewUitvoering.setText(mData.get(position).getUitvoering());
            // get URL
            ImageView image = (ImageView) convertView.findViewById(R.id.cardImage);
            fotoURL = mData.get(position).geturl();
            if (fotoURL != null)
                Picasso.with(getBaseContext()).load(fotoURL).into(image);
            else
                Picasso.with(getBaseContext()).load("http://www.colorhexa.com/333333.png").into(image);


            //Click card
            convertView.findViewById(R.id.btnbekijk).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CardViewActivity.class);
                    intent.putExtra("ONDERWERP", mData.get(position).getOnderwerp());
                    intent.putExtra("TITLE", mData.get(position).getTitel());
                    intent.putExtra("DOEL", mData.get(position).getDoel());
                    intent.putExtra("UITKOMST", mData.get(position).getUitvoering());
                    intent.putExtra("INZET", mData.get(position).getInzet());
                    intent.putExtra("URL", mData.get(position).geturl());
                    intent.putExtra("OPDRACHT", mData.get(position).getOpdracht());
                    intent.putExtra("BRONNEN", mData.get(position).getBronnen());
                    startActivity(intent);
                }
            });

            return convertView;

        }


    }

}
