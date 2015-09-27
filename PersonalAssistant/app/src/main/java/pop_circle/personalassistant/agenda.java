package pop_circle.personalassistant;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class agenda extends ListActivity {

    private ArrayList<ListItem> data;
    PaDbHelper db;
    String ownerID; // Need to recieve the owner ID to know who youre editing
    String dateLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This gets the date and month that the user clicked on, passed from CalenderActivity.java
        Intent iD = getIntent();
        Bundle bD = iD.getBundleExtra("CurrentDayAgenda");
        dateLine = bD.getString("dateLine");
        ownerID = bD.getString("ownerID");
        Log.wtf("test", "agenda : " + dateLine +  " owner Id: " + ownerID);

        db = new PaDbHelper(this);


        // setup the data source
        this.data = new ArrayList<ListItem>();


        ListItem item;
        for(int i = 0 ; i < 5; i++)
        {
            item = new ListItem("Event " +String.valueOf(i),"12:00" , String.valueOf(i));
            this.data.add(item);
        }


        setContentView(R.layout.activity_agenda);


        //Show the month and date on the event page
        TextView a_dateMonthLabel = (TextView) findViewById(R.id.agendaMonthYear);
        a_dateMonthLabel.setText(dateLine);
        eventAmount();


        // setup the data adaptor
        CustomAdapter adapter = new CustomAdapter(this, R.layout.agenda_list_item, this.data);


        // specify the list adaptor
        setListAdapter(adapter);

        //Event handlers
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String rowSelected = parent.getItemAtPosition(position).toString();
                String[] parts = rowSelected.split("#");
                String agendaName = parts[0];
                String agendaTime = parts[1];
                String agendaID = parts[2];

                Log.wtf("test", "Name: " + agendaName + " time: " + agendaTime + " ID: " + agendaID);

                /*
                Intent intent = new Intent(getApplicationContext(), add_events_activity.class);
                Bundle b = new Bundle();
                //Pass the date and the month selected to the add event activity
                b.putString("agendaID", agendaID);
                intent.putExtra("agendaEdit", b);
                startActivity(intent);
                */
            }
        });


        //To delete an entry
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String rowSelected = parent.getItemAtPosition(position).toString();
                String[] parts = rowSelected.split("#");
                String agendaID = parts[2];

                showDeleteAgendaDialog(agendaID);
                /*
                Intent intent = new Intent(getApplicationContext(), add_events_activity.class);
                Bundle b = new Bundle();
                //Pass the date and the month selected to the add event activity
                b.putString("agendaID", agendaID);
                intent.putExtra("agendaEdit", b);
                startActivity(intent);
                */
                return true;
            }
        });

    }


    /* Show the amount of events on this day */
    private void eventAmount(){
        int countEvent = db.getEventCount(dateLine,ownerID);
        TextView eventCountLabel = (TextView)findViewById(R.id.agendaEventsAmount);
        eventCountLabel.setText("Total: " + countEvent);
    }



    public void showDeleteAgendaDialog(String agendaID){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete this entry?");
        alertDialog.setMessage("Are you sure?");

        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Entry deleted", Toast.LENGTH_SHORT).show();
                //delete then reload this page

            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getApplicationContext(), "Entry deleted", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }




    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
        Log.wtf("test", "Item clicked " + id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
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