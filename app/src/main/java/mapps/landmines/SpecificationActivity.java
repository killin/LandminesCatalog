package mapps.landmines;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import mapps.landmines.model.DetailMineModel;
import mapps.landmines.model.MineModel;
import mapps.landmines.R;

import java.io.IOException;
import java.io.InputStream;

public class SpecificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // receiving parceable DetailMineModel drom DetailActivity
        Bundle b = getIntent().getExtras();
        DetailMineModel detailMineModel = b.getParcelable(".model.DetailMineModel");

        TextView mine_name = (TextView) findViewById(R.id.nameText);
        mine_name.setText(detailMineModel.getName());

        TextView width = (TextView) findViewById(R.id.width);
        TextView height = (TextView) findViewById(R.id.height);
        TextView weight = (TextView) findViewById(R.id.weight);
        TextView metallic_weight = (TextView) findViewById(R.id.metallic_weight);
        TextView explosive_weight = (TextView) findViewById(R.id.explosive_weight);
        TextView frag_range = (TextView) findViewById(R.id.frag_range);
        TextView charge_weight = (TextView) findViewById(R.id.charge_weight);


        if (detailMineModel.getWidth()!=null)
            width.setText(detailMineModel.getWidth());

        if (detailMineModel.getHeight()!=null)
            height.setText(detailMineModel.getHeight());

        if (detailMineModel.getWeight()!=null)
            weight.setText(detailMineModel.getWeight());

        if (detailMineModel.getMetallicWeight()!=null)
            metallic_weight.setText(detailMineModel.getMetallicWeight());

        if (detailMineModel.getExplosiveWeight()!=null)
        explosive_weight.setText(detailMineModel.getExplosiveWeight());

        if (detailMineModel.getFragRange()!=null)
        frag_range.setText(detailMineModel.getFragRange());

        if (detailMineModel.getChargeWeight()!=null)
        charge_weight.setText(detailMineModel.getChargeWeight());
//

        TextView component_materials = (TextView) findViewById(R.id.component_materials);
        TextView case_materials = (TextView) findViewById(R.id.case_materials);
        TextView detectability = (TextView) findViewById(R.id.detectability);
        TextView explosive = (TextView) findViewById(R.id.explosive);
        TextView transport = (TextView) findViewById(R.id.transport);
        TextView hazard = (TextView) findViewById(R.id.hazard);

        if (detailMineModel.getComponentMaterials()!=null)
            component_materials.setText(detailMineModel.getComponentMaterials());

        if (detailMineModel.getCaseMaterials()!=null)
            case_materials.setText(detailMineModel.getCaseMaterials());

        if (detailMineModel.getDetectability()!=null)
            detectability.setText(detailMineModel.getDetectability());

        if (detailMineModel.getExplosive()!=null)
            explosive.setText(detailMineModel.getExplosive());

        if (detailMineModel.getTransport()!=null)
            transport.setText(detailMineModel.getTransport());

        if (detailMineModel.getHazard()!=null)
            hazard.setText(detailMineModel.getHazard());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productId + ".jpg");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {

            try {
                stream = assetManager.open("H3661U02" + ".jpg");
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
            return BitmapFactory.decodeStream(stream);

        }
    }


}
