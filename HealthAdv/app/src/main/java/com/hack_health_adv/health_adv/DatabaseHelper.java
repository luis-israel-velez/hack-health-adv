package com.hack_health_adv.health_adv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import android.os.StrictMode;
import android.widget.Toast;



public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="hackdb";
    public static final String TABLE_NAME ="user_t";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
        /*Connection conn = null;

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionURL = null;
            String hostname = "luishackdbserver.database.windows.net";
            String dbname = "hackdb";
            String username = "hackadmin@luishackdbserver";
            String password = "Hack@2019";

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = String.format("jdbc:jtds:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                    + "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostname, dbname, username, password);
            connection = DriverManager.getConnection(url);
            //connection.close();
        }  catch (ClassNotFoundException e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            //Log.w("Error connection","" + e.getMessage());
        } catch (SQLException  e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            //Log.w("Error connection","" + e.getMessage());
        } catch (Exception e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            //Log.w("Error connection","" + e.getMessage());
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username_inp, String password_inp){
        //String[] columns = { COL_1 };
        //SQLiteDatabase db = getReadableDatabase();
        //String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        //String[] selectionArgs = { username, password };
        //Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        //int count = cursor.getCount();
        //cursor.close();
        //db.close();
        Connection connection = null;

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ConnectionURL = null;
            String hostname = "luishackdbserver.database.windows.net";
            String dbname = "hackdb";
            String username = "hackadmin@luishackdbserver";
            String password = "Hack@2019";

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = String.format("jdbc:jtds:sqlserver://%s:1433/hackdb;database=%s;user=%s;password=%s;encrypt=true;"
                    + "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostname, dbname, username, password);
            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_t WHERE username = '" + username_inp + "' and password = '" + password_inp + "' ;");
            while (rs.next()) {
                if (rs.getString("username") != null) {
                    connection.close();
                    return  true;
                }
            }
        }  catch (ClassNotFoundException e){

            e.printStackTrace();
        } catch (SQLException  e){

            e.printStackTrace();
        } catch (Exception e){

            e.printStackTrace();
        }

        return  false;
    }

    public String test(){
        Connection connection = null;
        String tes = "";

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ConnectionURL = null;
            String hostname = "luishackdbserver.database.windows.net";
            String dbname = "hackdb";
            String username = "hackadmin@luishackdbserver";
            String password = "Hack@2019";

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = String.format("jdbc:jtds:sqlserver://%s:1433/hackdb;database=%s;user=%s;password=%s;encrypt=true;"
                    + "integratedSecurity=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostname, dbname, username, password);
            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT * FROM user_t WHERE username = '" + "l" + "' and password = '" + "l1" + "' ;");
            ResultSet rs = stmt.executeQuery("select db_name() as db;");

            while (rs.next()) {
                tes += "Database: " + rs.getString("db");
            }
            //boolean isMoreThanOneRow = rs.first();
            //tes += " hi again ";
            connection.close();
            if(rs != null) {
                return  "Success + " + tes;
            }
        }  catch (ClassNotFoundException e){

            e.printStackTrace();
            return "Error - " + e.getMessage() + " " + tes;
        } catch (SQLException  e){

            e.printStackTrace();
            return "Error - " + e.getMessage() + " " + tes;
        } catch (Exception e){

            e.printStackTrace();
            return "Error - " + e.getMessage() + " " + tes;
        }

        return  tes;
    }
}