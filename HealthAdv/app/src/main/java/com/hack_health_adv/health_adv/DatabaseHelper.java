package com.hack_health_adv.health_adv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import android.os.StrictMode;
import android.widget.Toast;
import android.util.Log;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="hackdb";
    public static final String TABLE_NAME ="user_t";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";

    private Connection conn = null;
    private String hostname = "luishackdbserver.database.windows.net";
    private String driver = "net.sourceforge.jtds.jdbc.Driver";
    private String dbname = "hackdb";
    private String username = "hackadmin@luishackdbserver";
    private String password = "Hack@2019";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(driver);
            String url = String.format("jdbc:jtds:sqlserver://%s:1433/hackdb;database=%s;user=%s;password=%s;encrypt=true;"
                    + "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostname, dbname, username, password);
            conn = DriverManager.getConnection(url);
        } catch (Exception e){

        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(sqLiteDatabase);
    }

    public boolean checkUser(String usernameImp, String passwordImp){

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_t WHERE username = '" + usernameImp + "' and password = '" + passwordImp + "' ;");
            while (rs.next()) {
                if (rs.getString("username") != null) {
                    stmt.close();
                    return  true;
                }
            }
        }  catch (SQLException  e){
            Log.e(TAG, e.getMessage());
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        try { stmt.close(); } catch (Exception e) { }
        return  false;
    }


    public String addUser(String usernameImp, String passwordImp){
        Statement stmt = null;
        Boolean res = checkUser(usernameImp, passwordImp);
        String sql = "INSERT INTO user_t(username, password, household_count, created_date, last_login) " +
                "VALUES ('" + usernameImp + "', '" + passwordImp + "', 1, GETDATE(), CURRENT_TIMESTAMP);";
        if (res == false) {
            try {
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(sql);
                if (rs == 1) {
                    stmt.close();
                    return "Success";
                }
                return "Error " + rs;
            } catch (SQLException e) {
                Log.e(TAG, e.getMessage());
                return "Error SQLException - " + e.getMessage() + " " + sql;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                return "Error Exception - " + e.getMessage();
            }  finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) { }
            }
        }
        return "User Already Exist";
    }
}