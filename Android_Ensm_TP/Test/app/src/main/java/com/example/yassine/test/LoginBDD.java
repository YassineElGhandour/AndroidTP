package com.example.yassine.test;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginBDD
{
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD="login.db";
    private static final String TABLE_LOGIN="table_login";
    private static final String COL_ID="ID";
    private static final int NUM_COL_ID=0;
    private static final String COL_LOGIN="login";
    private static final int NUM_COL_LOGIN=1;
    private static final String COL_PASS="Pass";
    private static final int NUM_COL_PASS=2;
    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public LoginBDD(Context context){
        maBaseSQLite = new MaBaseSQLite(context,NOM_BDD,null,VERSION_BDD);
        open();
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public long insertlogin(Login login)
    {
        ContentValues values = new ContentValues();
        values.put(COL_LOGIN,login.getLogin());
        values.put(COL_PASS,login.getPass());
        return bdd.insert(TABLE_LOGIN,null,values);
    }

    public Login getLoginWithlogin(String log, String Pass){
        String[] selectionArgs = {log, Pass};
        String whereClause = COL_LOGIN + "=?" + " and " + COL_PASS +"=?" ;
        //Récupérer dans un Cursor les valeurs correspondantes à un login)
        Cursor c = bdd.query(TABLE_LOGIN, new String[] {
                COL_ID, COL_LOGIN, COL_PASS},whereClause , selectionArgs,null, null, null);
        return cursorToLogin(c);
    }


    private Login cursorToLogin(Cursor c){
        if (c.getCount() == 0) return null;
        c.moveToFirst();
        Login login = new Login();
        login.setId(c.getInt(NUM_COL_ID));
        login.setLogin(c.getString(NUM_COL_LOGIN));
        login.setPass(c.getString(NUM_COL_PASS));

        c.close();
        return login; }

    public void delete(Login l)
    {
        bdd.execSQL("DELETE FROM " + TABLE_LOGIN + " WHERE "+COL_LOGIN+" = '"+l.getLogin()+"' and "+COL_PASS+" = '"+l.getPass()+"' ");
    }
}
