package pop_circle.personalassistant;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by Stashie on 2015/09/27.
 */
public class Budget extends AppCompatActivity {

    private Button addIncome, addExpense, visualize, popaddInc, popaddExp, reset;
    private TextView income, expense, remainder;
    private PaDbHelper db;
    private int user;
    private double inc, exp, rem;
    final Context context = this;
    private FrameLayout vis;
    private PieChart mChart;
    private float[] yData;
    private String[] xData;
    private dbActions dba;
    private ProgressDialog pDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        dba = new dbActions();
        db =new PaDbHelper(this);
        vis = (FrameLayout) findViewById(R.id.visual);
        mChart = new PieChart(this);
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        vis.addView(mChart);
        vis.setBackgroundColor(Color.LTGRAY);
        mChart.setUsePercentValues(true);
        mChart.setDescription("Expenses");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(12);
        mChart.setTransparentCircleRadius(15);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null)
                    return;

                Toast.makeText(Budget.this, xData[entry.getXIndex()] +  " R" + entry.getVal() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
       // if (requestCode == 1) {
            //if(resultCode == RESULT_OK){
                user= ((MyApplication) this.getApplication()).getLoggedUser();
                if(user != -1) {

                    Log.wtf("THE USER WHOM HAS LOGGETH IN", "details " + user);
                    addIncome = (Button) findViewById(R.id.btnIncome);
                    addExpense = (Button) findViewById(R.id.btnExp);
                    reset = (Button) findViewById(R.id.btnres);
                  //  visualize = (Button) findViewById(R.id.btnVisualize);
                    income = (TextView) findViewById(R.id.editIncome);
                    expense = (TextView) findViewById(R.id.editExp);
                    remainder = (TextView) findViewById(R.id.editRem);
                    updateValues();


                    Legend l = mChart.getLegend();
                    l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
                    l.setXEntrySpace(7);
                    l.setXEntrySpace(5);

                    reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            db.resetBudget(user);
                            updateValues();
                        }
                    });

                            addIncome.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View arg0) {

                                    // get prompts.xml view
                                    LayoutInflater li = LayoutInflater.from(context);
                                    View promptsView = li.inflate(R.layout.popup_income, null);

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                            context);

                                    // set prompts.xml to alertdialog builder
                                    alertDialogBuilder.setView(promptsView);

                                    final EditText userInput = (EditText) promptsView
                                            .findViewById(R.id.edtInAmount);

                                    alertDialogBuilder
                                            .setCancelable(false)
                                            .setPositiveButton("OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            db.updateIncome(Double.parseDouble(userInput.getText().toString()), user);
                                                            updateValues();
                                                            //finish();
                                                            //result.setText(userInput.getText());
                                                        }
                                                    })
                                            .setNegativeButton("Cancel",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });

                                    // create alert dialog
                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                    // show it
                                    alertDialog.show();

                                }
                            });

                    addExpense.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {

                            // get prompts.xml view
                            LayoutInflater li = LayoutInflater.from(context);
                            View promptsView = li.inflate(R.layout.popup_expense, null);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    context);

                            // set prompts.xml to alertdialog builder
                            alertDialogBuilder.setView(promptsView);

                            final EditText userInput = (EditText) promptsView.findViewById(R.id.edtExAmount);
                            final Spinner userExChoice = (Spinner) promptsView.findViewById(R.id.spinEx);

                            // set dialog message

                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // get user input and set it to result
                                                    db.UpdateExpense(Double.parseDouble(userInput.getText().toString()), userExChoice.getSelectedItem().toString(), user);
                                                    updateValues();
                                                }
                                            })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        }
                    });
                }
        }



    private void updateValues()
    {
        yData = new float[]{(float)db.getHouse(user),(float)db.getFood(user),(float) db.getCredit(user), (float)db.getClothes(user), (float)db.getLux(user), (float)db.getCon(user), (float)db.getLoans(user)};
        xData = new String[]{"Household", "Food", "Credit", "Clothes", "Luxury", "Contract", "Loans"};
        addData();
        inc = db.getIncome(user);
        exp = db.getExpense(user);
        rem = inc - exp;
        income.setText("R" + String.valueOf(inc));
        expense.setText("R" +String.valueOf(exp));
        remainder.setText("R" + String.valueOf(rem));
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
            pDialog = new ProgressDialog(Budget.this);
            pDialog.setMessage("Updating Budget..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            updateServer();
            finish();
            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

    private void updateServer()
    {
        float incomeS = (float)db.getIncome(user);
        float totalExpS = (float)db.getExpense(user);
        float houseS = (float)db.getHouse(user);
        float foodS = (float)db.getFood(user);
        float creditS = (float)db.getCredit(user);
        float clothesS = (float)db.getClothes(user);
        float luxS = (float)db.getLux(user);
        float conS = (float)db.getCon(user);
        float loanS = (float)db.getLoans(user);

        dba.updateBudget(user, incomeS, totalExpS, houseS, foodS, creditS, clothesS, luxS, conS, loanS);
    }

    private void addData()
    {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<String> xVals1 = new ArrayList<String>();
        for(int i=0; i<yData.length; i++)
        {
            yVals1.add(new Entry(yData[i], i));
        }


        for(int i=0; i<xData.length; i++)
        {
            xVals1.add(xData[i]);
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "Expenses");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        ArrayList<Integer> colours = new ArrayList<Integer>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colours.add(c);

        for(int c: ColorTemplate.JOYFUL_COLORS)
            colours.add(c);

        for(int c: ColorTemplate.COLORFUL_COLORS)
            colours.add(c);

        for(int c: ColorTemplate.LIBERTY_COLORS)
            colours.add(c);

        for(int c: ColorTemplate.PASTEL_COLORS)
            colours.add(c);

        colours.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colours);

        PieData data = new PieData(xVals1, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);


        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();


    }
}

