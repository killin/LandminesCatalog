package mapps.landmines.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mapps.landmines.model.DetailMineModel;
import mapps.landmines.model.MineModel;

/**
 * Created by RK on 9/30/2016.
 */
public class MinesDataSource {

    public static final String TAG="MinesDataSource";

    MinesDBOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] basicColumns = {
            MinesDBOpenHelper.MINES_ID,
            MinesDBOpenHelper.NAME,
            MinesDBOpenHelper.ORDINANCE_TYPE,
            MinesDBOpenHelper.COUNTRY_OF_ORIGIN,
            MinesDBOpenHelper.COUNTRIES_USED_IN,
            MinesDBOpenHelper.IMAGE_FILENAME};


    public MinesDataSource(Context context){
        dbhelper = new MinesDBOpenHelper(context);

        try {

            dbhelper.createDataBase();

        } catch (IOException e) {

            Log.e(TAG, "Unable to create database" + e.getMessage());
            throw new Error("Unable to create database");

        }

        try {

            dbhelper.openDataBase();

        }catch(SQLException sqle){

            Log.e(TAG, "sql exception occured " + sqle.getMessage());
            throw sqle;

        }
    }

    public List<MineModel> findAll() {

        Cursor cursor = database.query(MinesDBOpenHelper.MINES_TABLE, basicColumns,
                null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        List<MineModel> tours = cursorToList(cursor);

        if (cursor != null)
            cursor.close();

        return tours;
    }

    public DetailMineModel findMineWithID(String id) {

        Cursor cursor = database.query(MinesDBOpenHelper.MINES_TABLE, MinesDBOpenHelper.Details_COLUMNS,
                MinesDBOpenHelper.MINES_ID + "=?", new String[]{id }, null, null, null, null);

        DetailMineModel mine=null;

        if (cursor != null) {
            cursor.moveToFirst();

            mine = new DetailMineModel();
            mine.setMineId(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.MINES_ID)));
            mine.setName(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.NAME)));
            mine.setType(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.ORDINANCE_TYPE)));
            mine.setCountryOrigin(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.COUNTRY_OF_ORIGIN)));
            mine.setCountriesUsed(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.COUNTRIES_USED_IN)));
            mine.setImageFilename(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.IMAGE_FILENAME)));

            mine.setDescription(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.DESCRIPTION)));
            mine.setAssemblies(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.ASSEMBLIES)));
            mine.setMethodOfOperation(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.METHOD_OF_OPERATION)));
            mine.setStructure(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.STRUCTURE)));
            mine.setMarkings(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.MARKINGS)));
            mine.setAlert(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.ALERT)));

            mine.setWidth(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.WIDTH)));
            mine.setHeight(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.HEIGHT)));
            mine.setWeight(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.WEIGHT)));
            mine.setMetallicWeight(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.METALLIC_WEIGHT)));
            mine.setExplosiveWeight(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.EXPLOSIVE_WEIGHT)));
            mine.setFragRange(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.FRAG_RANGE)));
            mine.setChargeWeight(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.CHARGE_WEIGHT)));
            mine.setComponentMaterials(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.COMPONENT_MATERIALS)));
            mine.setCaseMaterials(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.CASE_MATERIALS)));
            mine.setDetectability(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.DETECTABILITY)));
            mine.setExplosive(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.EXPLOSIVE)));
            mine.setTransport(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.TRANSPORT)));
            mine.setHazard(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.HAZARD)));
            mine.setImages(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.IMAGES)));

            mine.setNeutralization(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.NEUTRALIZATION)));
            mine.setDisarming(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.DISARMING)));
            mine.setPlacement(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.PLACEMENT)));

            Log.i(TAG, "Returned: id="  + mine.getMineId() + " ,name=" + mine.getName());

            cursor.close();
        }

        return mine;
    }

    private List<MineModel> cursorToList(Cursor cursor) {
        List<MineModel> mineList = new ArrayList<MineModel>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                MineModel mine = new MineModel();
                mine.setMineId(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.MINES_ID)));
                mine.setName(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.NAME)));
                mine.setType(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.ORDINANCE_TYPE)));
                mine.setCountryOrigin(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.COUNTRY_OF_ORIGIN)));
                mine.setCountriesUsed(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.COUNTRIES_USED_IN)));
                mine.setImageFilename(cursor.getString(cursor.getColumnIndex(MinesDBOpenHelper.IMAGE_FILENAME)));

                mineList.add(mine);
            }
        }
        return mineList;
    }

    public void open() {
        Log.i(TAG, "Database opened");
        database = dbhelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database closed");
        dbhelper.close();
    }

}
