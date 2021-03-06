package pop_circle.personalassistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskManager extends AppCompatActivity {
    List<Task> list;
    MyAdapter adapt;
    PaDbHelper db;
    private dbActions dba;
    private ProgressDialog pDialog;
    boolean changesMade = false;
    int user;

    List<Integer> checkedYe = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
       // db is a variable of type PaDbHelper
        db=new PaDbHelper(this);
        dba = new dbActions();
        list=db.getAllTasks(user);
        adapt=new MyAdapter(this,R.layout.listitems , list);
        ListView listTask=(ListView)findViewById(R.id.listView1);
        listTask.setAdapter((ListAdapter) adapt);
    }

    public void addTaskNow(View v) {
        //junk comment
        EditText t = (EditText) findViewById(R.id.task);
        String s = t.getText().toString();
        if (s.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter in a task first",
            Toast.LENGTH_LONG).show();
        } else {
            Task task = new Task(s, 0);
            db.addTask(task, user);
            changesMade = true;
            Log.d("task", "data added");
            t.setText("");
            adapt.add(task);
            adapt.notifyDataSetChanged();


        }
    }

    private class MyAdapter extends ArrayAdapter<Task> {
        Context context;
        List<Task> taskList = new ArrayList<Task>();
        int layoutResourceId;

        public MyAdapter(Context context, int layoutResourceId,
                         List<Task> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.taskList = objects;
            this.context = context;
        }

        /**
         * This method will DEFINe what the view inside the list view will finally look like
         * Here we are going to code that the checkbox state is the status of task and
         * check box text is the task name
         */
        int i = 0;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            CheckBox chk = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitems,
                        parent, false);
                chk = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(chk);

                chk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Task changeTask = (Task) cb.getTag();
                        changeTask.setChecked(cb.isChecked() == true ? 1 : 0);

                        //db.updateTask(changeTask);
                        db.deleteTask(changeTask);
                        changesMade = true;
                        adapt.notifyDataSetChanged();
                        finish();
                        startActivity(getIntent());

                        Toast.makeText(
                                getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() + " is "
                        + cb.isChecked(), Toast.LENGTH_LONG)
                        .show();
                    }
                });
            } else {

                chk = (CheckBox) convertView.getTag();
            }

            Task current = taskList.get(position);
            chk.setText(current.getName());

            chk.setChecked(current.getChecked() == 1 ? true : false);

            chk.setTag(current);


           // Log.d("listener", String.valueOf(current.getId()));
            return convertView;
        }

    }

    @Override
    public void onBackPressed() {
        new updateBudget().execute(); // update server when leaving activity
        //updateServer();
        // finish();
    }

    class updateBudget extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TaskManager.this);
            pDialog.setMessage("Updating Tasks..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            if(changesMade) {
                updateTasks();
            }
            finish();
            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

    private void updateTasks()
    {
        list=db.getAllTasks(user);
        dba.clearTasks(user);
        for(int i=0; i<list.size(); i++)
        {
            dba.addTask(user, list.get(i).getName());
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
