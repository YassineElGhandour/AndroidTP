package com.example.yassine.test;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;



public class MaBaseSQLite extends SQLiteOpenHelper {
    private static final String TABLE_LOGIN="table_login";
    private static final String COL_ID="ID";
    private static final String COL_login="Login";
    private static final String COL_pass="Pass";

    private static final String TABLE_ETAB="table_etab";
    private static final String COL_ID_ETAB="ID_ETAB";
    private static final String COL_NOM_ETAB="nom_etab";
    private static final String COL_LABEL_ETAB="label_etab";
    private static final String COL_LAT_ETAB="lat_etab";
    private static final String COL_LOG_ETAB="log_etab";


    private static final String CREATE_BDD = "CREATE TABLE "+TABLE_LOGIN+" ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_login+" TEXT NOT NULL, "+COL_pass+" TEXT NOT NULL);";

    private static final String CREATE_BDD_ETAB  ="CREATE TABLE "+TABLE_ETAB+" ("+COL_ID_ETAB+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NOM_ETAB+" TEXT NOT NULL, "+COL_LABEL_ETAB+" TEXT NOT NULL , "+COL_LAT_ETAB+" TEXT NOT NULL , "+COL_LOG_ETAB+" TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_BDD_ETAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_LOGIN+";");
        db.execSQL("DROP TABLE "+TABLE_ETAB+";");
        onCreate(db);
    }
}
