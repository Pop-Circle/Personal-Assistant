package pop_circle.personalassistant;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Stashie on 2015/10/11.
 */
public class dbActions extends AppCompatActivity {

    Connection connect;
    //String ipaddress, db, username, password;
    Statement st;
    TextView errorlbl;
    static final private String ipaddress = "b885de2b-0ef0-44b7-aad7-a52e011ba2a9.sqlserver.sequelizer.com";
    static final private String db = "dbb885de2b0ef044b7aad7a52e011ba2a9";
    static final private String username = "yswhamzdokntwkno";
    static final private String password = "JzRRLxEBpxpGLPyj7BGpAhiTmMvHywZFAjRkLErHnzcLowC37vXz2qhBr7D7c5c2";

    private Connection ConnectionHelper(String user, String password,
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
            //ConnectionURL = "jdbc:mysql://imy.up.ac.za/"
            connection = DriverManager.getConnection(ConnectionURL);
            //connection = DriverManager.getConnection("jdbc:mysql://imy.up.ac.za/PopCircle", "PopCircle", "pSaXCaw7");
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ipaddress = "imy.up.ac.za";
        //db = "PopCircle";
        //username = "PopCircle";
       // password = "pSaXCaw7";
//        int i =0;
//        while(i <5) {
//            connect = ConnectionHelper(username, password, db, ipaddress);
//            try {
//                st = connect.createStatement();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            i++;
//        }
    }

    public int regUser(String _name, String pass, String em)
    {
        int i =0;
        while(i <5 && connect == null) {
            connect = ConnectionHelper(username, password, db, ipaddress);
            try {
                st = connect.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            i++;
        }
        try {

            int rs = st.executeUpdate("INSERT INTO users(username, password, email) VALUES('" + _name + "', '"+ pass+"', '"+ em+"')");
            return rs;


        } catch (SQLException e) {
            errorlbl.setText(e.getMessage().toString());
        }
        return -1;
    }

    public ResultSet loginUser(String _name, String pass)
    {
        try {
            st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from users where username='" + _name + "' and password ='" + pass + "'");
            return rs;


        } catch (SQLException e) {
            errorlbl.setText(e.getMessage().toString());
        }
        return null;
    }
    /*
    private static final String KEY_BUDGETIDTASK ="TaskUserId";
    private static final String KEY_INCOME= "income";
    private static final String KEY_TOTEX= "totalExpenses";
    private static final String KEY_HOUSEHOLD= "household";
    private static final String KEY_FOOD= "food";
    private static final String KEY_CREDIT= "credit";
    private static final String KEY_CLOTHES= "clothes";
    private static final String KEY_LUXURY= "luxury";
    private static final String KEY_CONTRACTS= "contracts";
    private static final String KEY_LOANS= "loans";
     */
    public int getUserId(String userName) {
        try {
            st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT userId from users where username='"+userName+"'");
            ResultSet qs = st.executeQuery("INSERT INTO budget (TaskUserId, income, totalExpenses, household, food, credit, clothes, luxury, contracts, loans) VALUES('"+rs.getInt("userId")+"', 0, 0, 0, 0, 0, 0, 0, 0, 0)");

            return rs.getInt("userId");


        } catch (SQLException e) {
            errorlbl.setText(e.getMessage().toString());
        }
        return -1;
    }
}
