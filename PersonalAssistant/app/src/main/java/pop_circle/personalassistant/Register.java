package pop_circle.personalassistant;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stashie on 2015/09/26.
 */
public class Register extends AppCompatActivity{
    private EditText name;
    private EditText pass;
    private EditText email;
    private TextView log;
    private Button regButt;
    private dbActions db;
    TextView errorlbl;
    int rs;
    private PaDbHelper dbS;
    private ProgressDialog pDialog;
    private static String url_create_user = "http://imy.up.ac.za/PopCircle/Php/db_addUser.php";
    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser = new JSONParser();

    /*private Connection ConnectionHelper(String user, String password,
                                        String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";"
                    + "databaseName=" + database + ";user=" + user
                    + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.editName);
        pass = (EditText) findViewById(R.id.editPass);
        email = (EditText) findViewById(R.id.editEmail);
        regButt = (Button) findViewById(R.id.buttReg);
        log = (TextView) findViewById(R.id.txtLoginClick);
        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        db = new dbActions();
        dbS =new PaDbHelper(this);
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
        new CreateNewUser().execute(_name, password, em);

        Log.wtf("test", "details " + _name + " " + password + " " + em);
        //db.addUser(_name, password, em);
        //finish();
    }

    class CreateNewUser extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Adding User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            String _name = args[0];
            String _pass = args[1];
            String em = args[2];
            rs = db.regUser(_name, _pass, em);
            int id = db.getUserId(_name);
            if (rs >0) {
                dbS.addUser(id,_name, _pass, em);
                finish();
            } else {
                //errorlbl.setText("Sorry, wrong credentials!!!");
            }
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("username", _name));
            //params.add(new BasicNameValuePair("password", _pass));
            //params.add(new BasicNameValuePair("email", em));

            // getting JSON Object
            // Note that create product url accepts POST method
            //JSONObject json = jsonParser.makeHttpRequest(url_create_user,"POST", params);

            // check log cat fro response
           // Log.d("Create Response", json.toString());

            // check for success tag
            //try {
            //    int success = json.getInt(TAG_SUCCESS);

            //    if (success == 1) {
                    // successfully created product
                    //Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                   // startActivity(i);

                    // closing this screen
             //       finish();
             //   } else {
                    // failed to create product
            //    }
           // } catch (JSONException e) {
           //     e.printStackTrace();
           // }

            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}
