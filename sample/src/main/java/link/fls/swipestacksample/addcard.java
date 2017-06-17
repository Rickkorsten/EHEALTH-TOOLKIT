package link.fls.swipestacksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class addcard extends AppCompatActivity {

    EditText getdoel, getinzet, getonderwerp, gettitel, getuitvoering;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        getdoel   = (EditText)findViewById(R.id.makedoel);
    }
}
