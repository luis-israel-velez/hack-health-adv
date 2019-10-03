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
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
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

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public String test(){
        //Log.i("Android"," MySQL Connect Example.");
        Connection conn = null;
        String tes = "";

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            //Class.forName(driver).newInstance();
            //String connString = "jdbc:sqlserver://luishackdbserver.database.windows.net:1433;database=hackdb;user=hackadmin@luishackdbserver;password=Hack@2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionURL = null;
            String hostname = "luishackdbserver.database.windows.net";
            String dbname = "hackdb";
            String username = "hackadmin@luishackdbserver";
            String password = "Hack@2019";

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //ConnectionURL = "jdbc:jtds:sqlserver://" + hostname  + dbname + ":1433" + ";user=" + username+ ";password=" + password + ";";
            String url = String.format("jdbc:jtds:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                            + "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostname, dbname, username, password);
           // tes += url;
            connection = DriverManager.getConnection(url);


            //jdbc:sqlserver://luishackdbserver.database.windows.net:1433;database=hackdb;user=hackadmin@luishackdbserver;password=Hack@2019

            //conn = DriverManager.getConnection(url);
            tes += "2here";
            //Log.w("Connection","open");
            //Statement stmt = conn.createStatement();
            //ResultSet reset = stmt.executeQuery("select * from user_t");
            //Print the data to the console
            //while(reset.next()){
                //Log.w("Data:",reset.getString(3));
                //              Log.w("Data",reset.getString(2));
            //}
            connection.close();
            conn.close();
        }  catch (ClassNotFoundException e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            tes += "Class Error + " + e.getMessage();
            return tes;
            //Log.w("Error connection","" + e.getMessage());
        } catch (SQLException  e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            tes += "SQL Error + " + e.getMessage();
            return tes;
            //Log.w("Error connection","" + e.getMessage());
        } catch (Exception e){

            e.printStackTrace();
            //displayExceptionMessage(e.getMessage());
            tes += "Error + " + e.getMessage();
            return tes;
            //Log.w("Error connection","" + e.getMessage());
        }

        return tes;

    }
}