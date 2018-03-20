package com.example.yassine.test;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EtabBDD
{
    private static final int VERSION_BDD_1 = 1;
    private static final String NOM_BDD_ETAB = "login2.db";
    private static final String TABLE_ETAB = "table_etab";
    private static final String COL_ID_ETAB = "ID_ETAB";
    private static final int NUM_COL_ID_ETAB = 0;
    private static final String COL_NOM_ETAB = "nom_etab";
    private static final int NUM_COL_NOM_ETAB = 1;
    private static final String COL_LABEL_ETAB = "label_etab";
    private static final int NUM_COL_LABEL_ETAB= 2;
    private static final String COL_LAT_ETAB="lat_etab";
    private static final int NUM_COL_LAT_ETAB= 3;
    private static final String COL_LOG_ETAB="log_etab";
    private static final int NUM_COL_LOG_ETAB= 4;

    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public EtabBDD(Context context){
        maBaseSQLite = new MaBaseSQLite(context,NOM_BDD_ETAB,null, VERSION_BDD_1);
        open();
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public long insertEtab(Etablissement etab)
    {
        ContentValues values = new ContentValues();
        values.put(COL_NOM_ETAB,etab.getName());
        values.put(COL_LABEL_ETAB,etab.getLabel());
        values.put(COL_LAT_ETAB,etab.getLat());
        values.put(COL_LOG_ETAB,etab.getLog());
        return bdd.insert(TABLE_ETAB,null,values);
    }

    public ArrayList<Etablissement> getAllInformationNotDetailed()
    {
        ArrayList<Etablissement> Etablissements= new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_ETAB;
        Cursor c = bdd.rawQuery(query, null);
        if (c.moveToFirst()){
            do {
                Etablissement e = new Etablissement(c.getInt(0), c.getString(1), c.getString(2),c.getDouble(3),c.getDouble(4), R.drawable.logo);
                Etablissements.add(e);
            } while(c.moveToNext());
        }
        c.close();
        bdd.close();
        return Etablissements;
    }

    public Etablissement getAllInformation(String nomEtab, String labelEtab){
        String[] selectionArgs = {nomEtab, labelEtab};
        String whereClause = COL_NOM_ETAB + "=?" + " and " + COL_LABEL_ETAB +"=?";

        Cursor c = bdd.query(TABLE_ETAB, new String[]
                {
                    COL_ID_ETAB, COL_NOM_ETAB, COL_LABEL_ETAB } ,whereClause , selectionArgs,null, null, null);
        return cursorToEtab(c);
    }


    private Etablissement cursorToEtab(Cursor c){
        if (c.getCount() == 0) return null;
        c.moveToFirst();
        Etablissement etab = new Etablissement();
        etab.setIdEtab(c.getInt(NUM_COL_ID_ETAB));
        etab.setName(c.getString(NUM_COL_NOM_ETAB));
        etab.setLabel(c.getString(NUM_COL_LABEL_ETAB));
        etab.setLat(c.getDouble(NUM_COL_LAT_ETAB));
        etab.setLog(c.getDouble(NUM_COL_LOG_ETAB));


        c.close();
        return etab; }

    public void deleteEtab(Etablissement l)
    {
        bdd.execSQL("DELETE FROM " + TABLE_ETAB + " WHERE "+COL_NOM_ETAB+" = '"+l.getName());
    }
}
