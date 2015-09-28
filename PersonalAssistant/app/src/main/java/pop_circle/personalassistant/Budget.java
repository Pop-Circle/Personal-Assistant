package pop_circle.personalassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * Created by Stashie on 2015/09/27.
 */
public class Budget extends AppCompatActivity {

    private Button addIncome, addExpense, visualize, popaddInc, popaddExp;
    private TextView income, expense, remainder;
    private PaDbHelper db;
    private int user;
    private double inc, exp, rem;
    final Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        db =new PaDbHelper(this);
        user= ((MyApplication) this.getApplication()).getLoggedUser();
        if (user == -1)
        {
            Intent intent = new Intent(Budget.this, Login.class);
            startActivity(intent);
        }
        else
        {
            user= ((MyApplication) this.getApplication()).getLoggedUser();
            addIncome = (Button) findViewById(R.id.btnIncome);
            addExpense = (Button) findViewById(R.id.btnExp);
            visualize = (Button) findViewById(R.id.btnVisualize);
            income = (TextView) findViewById(R.id.editIncome);
            expense = (TextView) findViewById(R.id.editExp);
            remainder = (TextView) findViewById(R.id.editRem);
            updateValues();
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

                /*popaddInc = (Button)  findViewById(R.id.addInc);
                popaddInc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText amount = (EditText) findViewById(R.id.edtInAmount);
                        db.updateIncome(Double.parseDouble(amount.getText().toString()), user);
                        updateValues();
                        finish();
                    }
                });*/

                    // set dialog message

                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text
                                            //EditText amount = (EditText) findViewById(R.id.edtInAmount);
                                            db.updateIncome(Double.parseDouble(userInput.getText().toString()), user);
                                            updateValues();
                                            //finish();
                                            //result.setText(userInput.getText());
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
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

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.edtExAmount);

                    // set dialog message
                /*
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });*/

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            });

            // popupWindow.showAsDropDown(addIncome, 50, -30);

        }


    }

    private void updateValues()
    {
        inc = db.getIncome(user);
        exp = db.getExpense(user);
        rem = inc - exp;
        income.setText("R" + String.valueOf(inc));
        expense.setText("R" +String.valueOf(exp));
        remainder.setText("R" + String.valueOf(rem));
    }
}

