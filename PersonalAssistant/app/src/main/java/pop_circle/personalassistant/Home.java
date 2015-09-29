/*package pop_circle.personalassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Stashie on 2015/09/29.
 */
/*
public class Home extends AppCompatActivity {
    int user;
    Button bCal, bBud,bTask, bPers;
    TextView welcome;
    PaDbHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        user= ((MyApplication) this.getApplication()).getLoggedUser();

        db = new PaDbHelper(this);
        if (user == -1)
        {
            Intent intent = new Intent(Home.this, Login.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        welcome = (TextView) findViewById(R.id.welcomeText);
        bCal = (Button) findViewById(R.id.btnCal);
        bBud = (Button) findViewById(R.id.btnBudget);
        bTask = (Button) findViewById(R.id.btnTasks);
        bPers = (Button) findViewById(R.id.btnPersonal);


        welcome.setText("Welcome " + db.getUserName(user) + ".");

        bCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, calendarActivity.class);//im not sure if this is where you want to land
                startActivityForResult(intent, 1);
            }
        });

        bBud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Budget.class);//im not sure if this is where you want to land
                startActivityForResult(intent, 1);
            }
        });

        bTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TaskManager.class);//im not sure if this is where you want to land
                startActivityForResult(intent, 1);
            }
        });

        bPers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Personalize.class);//im not sure if this is where you want to land
                startActivityForResult(intent, 1);
            }
        });
    }
}
*/
package pop_circle.personalassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    PaDbHelper db;
    int user;
    Button bCal, bBud,bTask, bPers;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        // db is a variable of type PaDbHelper
        db=new PaDbHelper(this);

        if (user == -1)
        {
            Intent intent = new Intent(Home.this, Login.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        if(user !=-1) {
            welcome = (TextView) findViewById(R.id.welcomeText);
            bCal = (Button) findViewById(R.id.btnCal);
            bBud = (Button) findViewById(R.id.btnBudget);
            bTask = (Button) findViewById(R.id.btnTasks);
            bPers = (Button) findViewById(R.id.btnPersonal);


            welcome.setText("Welcome " + db.getUserName(user) + ".");

            bCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, calendarActivity.class);//im not sure if this is where you want to land
                    startActivityForResult(intent, 1);
                }
            });

            bBud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Budget.class);//im not sure if this is where you want to land
                    startActivityForResult(intent, 1);
                }
            });

            bTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, TaskManager.class);//im not sure if this is where you want to land
                    startActivityForResult(intent, 1);
                }
            });

            bPers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Personalize.class);//im not sure if this is where you want to land
                    startActivityForResult(intent, 1);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_task_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

