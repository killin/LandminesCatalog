package mapps.landmines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import mapps.landmines.model.DetailMineModel;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // receiving parcelable DetailMineModel from DetailActivity
        Bundle b = getIntent().getExtras();
        DetailMineModel detailMineModel = b.getParcelable(".model.DetailMineModel");

        TextView mine_name = (TextView) findViewById(R.id.nameText);
        mine_name.setText(detailMineModel.getName());

        TextView description = (TextView) findViewById(R.id.description);
        TextView assemblies = (TextView) findViewById(R.id.assemblies);
        TextView methodOfOperation = (TextView) findViewById(R.id.methodOfOperation);
        TextView structure = (TextView) findViewById(R.id.structure);
        TextView markings = (TextView) findViewById(R.id.markings);
        TextView alert = (TextView) findViewById(R.id.alert);

        description.setText(detailMineModel.getDescription());
        assemblies.setText(detailMineModel.getAssemblies());
        methodOfOperation.setText(detailMineModel.getMethodOfOperation());
        structure.setText(detailMineModel.getStructure());
        markings.setText(detailMineModel.getMarkings());
        alert.setText(detailMineModel.getAlert());


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
