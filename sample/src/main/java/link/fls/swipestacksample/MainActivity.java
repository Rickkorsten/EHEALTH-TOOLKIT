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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, View.OnClickListener {

    private Button mButtonLeft, mButtonRight;
    private ButtonBarLayout mFab;
    private CardView cardviewcard;
    private ArrayList<TestModel> mData = new ArrayList<>();
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cards/Concepting");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot
                    HashMap<String, Object> row = (HashMap<String, Object>) postSnapshot.getValue();
                   // mData.add(new TestModel((String )row.get("doel"), (String) row.get("inzet"),(String)row.get("onderwerp"),(String)row.get("titel"),(String)row.get("uitvoering")));
                    mData.add((TestModel) postSnapshot.getValue(TestModel.class));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        mFab = (ButtonBarLayout) findViewById(R.id.fabAdd);
     //   cardviewcard = (CardView)findViewById(R.id.cardviewcard);

        mFab.setOnClickListener(this);
       //cardviewcard.setOnClickListener(this);

        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

        //fillWithTestData();
       // clickListenerCard();
    }

   //private void clickListenerCard(){
        // get cardview

             //   startActivity(new Intent(MainActivity.this, MenuActivity.class));
//Toast.makeText(getApplicationContext(),"baida",Toast.LENGTH_LONG).show();
       //     }


   // private void fillWithTestData() {
  //      for (int x = 0; x < 5; x++) {
   //         mData.add(new TestModel(getString(R.string.dummy_text) + " " + (x + 1), 1));
  //      }
  //  }

    @Override
    public void onClick(View v) {
         if (v.equals(mFab)) {
            startActivity(new Intent(MainActivity.this, CardViewActivity.class));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuReset:
                mSwipeStack.resetStack();
                Snackbar.make(mFab, R.string.stack_reset, Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.menuGitHub:
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://github.com/flschweiger/SwipeStack"));
                startActivity(browserIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewSwipedToRight(int position) {
        TestModel swipedElement = mAdapter.getItem(position);
        Toast.makeText(this, getString(R.string.view_swiped_right, swipedElement),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        TestModel swipedElement = mAdapter.getItem(position);
        Toast.makeText(this, getString(R.string.view_swiped_left, swipedElement),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
        Toast.makeText(this, R.string.stack_empty, Toast.LENGTH_SHORT).show();
    }

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
            textViewInzet.setText(mData.get(position).getInzet());
            // get onderwerp
            TextView textViewOnderwerp = (TextView) convertView.findViewById(R.id.titelinhoud);
            textViewOnderwerp.setText(mData.get(position).getOnderwerp());
            // get titel
            TextView textViewTitel = (TextView) convertView.findViewById(R.id.categorieinhoud);
            textViewTitel.setText(mData.get(position).getTitel());
            // get uitvoering
            TextView textViewUitvoering = (TextView) convertView.findViewById(R.id.uitvoeringinhoud);
            textViewUitvoering.setText(mData.get(position).getUitvoering());
            return convertView;

        }
    }
}
