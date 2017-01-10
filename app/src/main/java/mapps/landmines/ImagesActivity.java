package mapps.landmines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class ImagesActivity extends AppCompatActivity {

    public static final String TAG="ImagesActivity";
    public static final String FULL_IMAGE_ID = "1000";

    static final String STATE_IMAGES = "images_state";
    static final String STATE_MINE_NAME = "mine_name_state";

    private GlobalVariables appContext;

    String images;
    String mine_name;
    private String[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (GlobalVariables) getApplicationContext();

//        if (savedInstanceState != null)
//        {
//            images = savedInstanceState.getString(STATE_IMAGES);
//            mine_name = savedInstanceState.getString(STATE_MINE_NAME);
//        }else {
            // get the pass argument productId from DetailActivity
            // images filename delimited with comma
            images = getIntent().getStringExtra(DetailActivity.IMAGES_ID);
            mine_name = getIntent().getStringExtra(DetailActivity.MINE_NAME_ID);

        if (images == null){
            images = appContext.getImageStr();
            mine_name = appContext.getMine_name();
        }

        TextView name_tv = (TextView) findViewById(R.id.mine_name);
        name_tv.setText(mine_name);

        Log.d(TAG, " mine name:" + name_tv.getText());

        //split to array
        imageArray = getImageFiles(images);

        GridView imageGridview = (GridView) findViewById(R.id.images_gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(), imageArray);
        imageGridview.setAdapter(imageAdapter);

        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filename = imageArray[position];

                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra(FULL_IMAGE_ID, filename);
                startActivity(i);
                //finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String[] getImageFiles(String images){
        String[] items = images.split(",");
        return items;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        Log.d(TAG, " saving state :" + mine_name);

        savedInstanceState.putString(STATE_IMAGES, images);
        savedInstanceState.putString(STATE_MINE_NAME, mine_name);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        appContext.setImageStr(images);
        appContext.setMine_name(mine_name);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

            // Restore value of members from saved state
            images = savedInstanceState.getString(STATE_IMAGES);
            mine_name = savedInstanceState.getString(STATE_MINE_NAME);
        Log.d(TAG, " restoring state :" + mine_name);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
