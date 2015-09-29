package pop_circle.personalassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Stashie on 2015/09/26.
 */
public class Login extends AppCompatActivity{
    private EditText name;
    private EditText pass;
    private EditText email;
    private TextView reg;
    private Button logBut;
    private PaDbHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.editNameLog);
        pass = (EditText) findViewById(R.id.editPassLog);
        logBut = (Button) findViewById(R.id.butLogin);
        reg = (TextView) findViewById(R.id.txtRegisterClick);
        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
            });
        db =new PaDbHelper(this);
        logBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(v == logBut){
                    loginUser();
                }
            }
        });
    }


    public void loginUser()
    {
        if(db.login(name.getText().toString(), pass.getText().toString()) >0)
        {

            ((MyApplication) this.getApplication()).setUserId(db.getUserId(name.getText().toString()));
            //Intent returnIntent = new Intent();
            //returnIntent.putExtra("result","finish");
            //setResult(RESULT_OK,returnIntent);
            finish();
        }
        else
        {
            CharSequence text = "Login Failed" ;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
