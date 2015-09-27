package pop_circle.personalassistant;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Stashie on 2015/09/26.
 */
public class Register extends AppCompatActivity{
    private EditText name;
    private EditText pass;
    private EditText email;
    private TextView log;
    private Button regButt;
    private PaDbHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.editName);
        pass = (EditText) findViewById(R.id.editPass);
        pass = (EditText) findViewById(R.id.editEmail);
        regButt = (Button) findViewById(R.id.buttReg);
        log = (TextView) findViewById(R.id.txtLoginClick);
        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
            });
        db =new PaDbHelper(this);
        regButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    registerUser();
            }
        });
    }


    public void registerUser()
    {
        String _name = name.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String em = email.getText().toString().trim();
        Log.wtf("test", "details " + _name + " " + password + " " + em);
        db.addUser(_name, password, em);
        finish();
    }
}
