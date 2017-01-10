package mapps.landmines.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by RK on 9/30/2016.
 */
public class MinesDBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "MinesDBOpenHelper";

    public static String DB_PATH = "/data/data/mapps.landmines/databases/";

    private SQLiteDatabase myDataBase;

    private Context context;

    //Constants for db name and version
    public static final String DATABASE_NAME = "mines.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String MINES_TABLE = "landmines";
    public static final String MINES_ID = "_id";

    // basic info
    public static final String NAME = "name";
    public static final String ORDINANCE_TYPE = "ordinance_type";
    public static final String COUNTRY_OF_ORIGIN = "country_of_origin";
    public static final String COUNTRIES_USED_IN = "countries_used_in";
    public static final String IMAGE_FILENAME = "image_name";


    public static final String[] BASIC_COLUMNS =
            {MINES_ID, NAME, ORDINANCE_TYPE,COUNTRY_OF_ORIGIN, COUNTRIES_USED_IN, IMAGE_FILENAME};



    // detail activity_description
    public static final String DESCRIPTION = "description";
    public static final String ASSEMBLIES = "assemblies";
    public static final String METHOD_OF_OPERATION  = "method_of_operation";
    public static final String STRUCTURE  = "structure";
    public static final String MARKINGS = "markings";
    public static final String ALERT = "alert";



    // specification
    //public static final String SPECIFICATIONS = "specifications";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String METALLIC_WEIGHT = "metallic_weight";
    public static final String EXPLOSIVE_WEIGHT = "explosive_weight";
    public static final String FRAG_RANGE = "frag_range";
    public static final String CHARGE_WEIGHT = "charge_weight";
    public static final String COMPONENT_MATERIALS = "component_materials";
    public static final String CASE_MATERIALS = "case_materials";
    public static final String DETECTABILITY = "detectability";
    public static final String EXPLOSIVE = "explosive";
    public static final String TRANSPORT = "transport";
    public static final String HAZARD = "hazard";
    public static final String IMAGES = "images";


    // disposal
    public static final String NEUTRALIZATION = "neutralization";
    public static final String DISARMING = "disarming";
    public static final String PLACEMENT = "placement";

    public static final String[] Details_COLUMNS =
            {MINES_ID, NAME, ORDINANCE_TYPE,COUNTRY_OF_ORIGIN, COUNTRIES_USED_IN, IMAGE_FILENAME,DESCRIPTION,
                    ASSEMBLIES, METHOD_OF_OPERATION,STRUCTURE, MARKINGS, ALERT,
					
					WIDTH, HEIGHT, WEIGHT, METALLIC_WEIGHT, EXPLOSIVE_WEIGHT, EXPLOSIVE_WEIGHT, FRAG_RANGE, 
					CHARGE_WEIGHT, COMPONENT_MATERIALS, CASE_MATERIALS, DETECTABILITY, EXPLOSIVE, TRANSPORT,
					HAZARD, IMAGES, NEUTRALIZATION, DISARMING, PLACEMENT
            };	

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + MINES_TABLE + " (" +
                    MINES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    ORDINANCE_TYPE + " TEXT, " +
                    COUNTRY_OF_ORIGIN + " TEXT, " +
                    COUNTRIES_USED_IN + " TEXT, " +
                    IMAGE_FILENAME + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    ASSEMBLIES + " TEXT, " +
                    METHOD_OF_OPERATION + " TEXT, " +
                    STRUCTURE + " TEXT, " +
                    MARKINGS + " TEXT, " +
                    WIDTH + " TEXT, " +
                    HEIGHT + " TEXT, " +
                    WEIGHT + " TEXT, " +
                    METALLIC_WEIGHT + " TEXT, " +
                    EXPLOSIVE_WEIGHT + " TEXT, " +
                    FRAG_RANGE + " TEXT, " +
                    CHARGE_WEIGHT + " TEXT, " +
                    COMPONENT_MATERIALS + " TEXT, " +
                    CASE_MATERIALS + " TEXT, " +
                    DETECTABILITY + " TEXT, " +
                    EXPLOSIVE + " TEXT, " +
                    TRANSPORT + " TEXT, " +
                    HAZARD + " TEXT, " +
                    IMAGES + " TEXT, " +
                    NEUTRALIZATION + " TEXT, " +
                    DISARMING + " TEXT, " +
                    PLACEMENT + " TEXT, " +
                    ALERT + " TEXT " +
                    ")";





    public MinesDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate()");
    }


    public void createDataBase()throws IOException{

        Log.d(TAG, "context.getApplicationInfo().dataDir:  " + context.getApplicationInfo().dataDir);

        boolean dbexist = checkdatabase();
        if(dbexist) {
            Log.d(TAG, "db exist");
        } else {

            this.getReadableDatabase();
            try {
                Log.d(TAG, "no db exist, copying mines.db to sdcard");

                copyDataBase();
            } catch(IOException e) {
                Log.e(TAG, "Error copying database" + e.getMessage());
                throw new Error("Error copying database");
            }
        }

    }


    private boolean checkdatabase() {
        boolean checkdb = false;
        try {

            File dbFile = context.getDatabasePath(DATABASE_NAME);
            checkdb =dbFile.exists();
            Log.d(TAG, "context.getDatabasePath(DATABASE_NAME)" + context.getDatabasePath(DATABASE_NAME));

        } catch(SQLiteException e) {
            Log.e(TAG, "Database doesn't exist" + e.getMessage());
        }
        return checkdb;
    }



    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024 * 32];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MINES_TABLE);
        onCreate(db);
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

}
