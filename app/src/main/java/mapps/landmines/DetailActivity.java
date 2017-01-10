package mapps.landmines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mapps.landmines.model.DetailMineModel;

public class DetailActivity extends AppCompatActivity {

    public static final String IMAGES_ID = "500";

    public static final String NEUTRALIZATION_ID = "100";
    public static final String DISARMING_ID = "200";
    public static final String PLACEMENT_ID = "300";
    public static final String MINE_NAME_ID = "400";

    DetailMineModel detailMineModel;

    private GlobalVariables appContext;
    static final String savedBundle = "DETAIL_BUNDLE";
    Bundle currentBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (GlobalVariables) getApplicationContext();

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            detailMineModel = appContext.getDetailMineModel();
        }
        else
        // receiving parcelable DetailMineModel
            detailMineModel = bundle.getParcelable(".model.DetailMineModel");

        TextView tv = (TextView) findViewById(R.id.nameText);
        tv.setText(detailMineModel.getName());

        TextView description = (TextView) findViewById(R.id.description);
        TextView assemblies = (TextView) findViewById(R.id.assemblies);
        TextView methodOfOperation = (TextView) findViewById(R.id.methodOfOperation);
        TextView structure = (TextView) findViewById(R.id.structure);
        TextView markings = (TextView) findViewById(R.id.markings);
        TextView alert = (TextView) findViewById(R.id.alert);

        description.setText(detailMineModel.getDescription());

        if (detailMineModel.getAssemblies() != null)
            assemblies.setText(detailMineModel.getAssemblies());

        if (detailMineModel.getMethodOfOperation() != null)
            methodOfOperation.setText(detailMineModel.getMethodOfOperation());

        if (detailMineModel.getStructure() != null)
            structure.setText(detailMineModel.getStructure());

        if (detailMineModel.getMarkings() != null)
            markings.setText(detailMineModel.getMarkings());

        if (detailMineModel.getAlert() != null)
            alert.setText(detailMineModel.getAlert());

        addListenerOnSpecButton(detailMineModel);

        addListenerOnImagesButton(detailMineModel.getName(), detailMineModel.getImages());

        String neutralization = detailMineModel.getNeutralization();
        String disarming = detailMineModel.getDisarming();
        String placement = detailMineModel.getPlacement();

        addListenerOnDisposalButton(neutralization, disarming, placement, detailMineModel.getName());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    void addListenerOnSpecButton(final DetailMineModel detailMineModel) {
        Button specBtn = (Button) findViewById(R.id.specsBtn);
        specBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open spec window
                //Toast.makeText(DetailActivity.this, "spec button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, SpecificationActivity.class);
                intent.putExtra(".model.DetailMineModel", detailMineModel);

                startActivity(intent);

            }
        });
    }


    void addListenerOnImagesButton(final String mine_name, final String images) {
        Button specBtn = (Button) findViewById(R.id.imagesBtn);
        specBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open images window
                //Toast.makeText(DetailActivity.this, "images button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, ImagesActivity.class);
                intent.putExtra(MINE_NAME_ID, mine_name);
                intent.putExtra(IMAGES_ID, images);

                startActivity(intent);

            }
        });
    }


    void addListenerOnDisposalButton(final String neutralization, final String disarming,
                                     final String placement, final String mine_name) {
        Button specBtn = (Button) findViewById(R.id.disposalBtn);
        specBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open disposal window
                //Toast.makeText(DetailActivity.this, "disposal button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, DisposalActivity.class);
                intent.putExtra(NEUTRALIZATION_ID, neutralization);
                intent.putExtra(DISARMING_ID, disarming);
                intent.putExtra(PLACEMENT_ID, placement);
                intent.putExtra(MINE_NAME_ID, mine_name);

                startActivity(intent);

            }
        });
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
	
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putBundle(savedBundle, currentBundle);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        appContext.setDetailMineModel(detailMineModel);

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        currentBundle = savedInstanceState.getBundle(savedBundle);

    }	

}
