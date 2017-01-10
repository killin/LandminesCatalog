package mapps.landmines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DisposalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String neutralization = getIntent().getStringExtra(DetailActivity.NEUTRALIZATION_ID);
        String disarming = getIntent().getStringExtra(DetailActivity.DISARMING_ID);
        String placement = getIntent().getStringExtra(DetailActivity.PLACEMENT_ID);
        String mine_name = getIntent().getStringExtra(DetailActivity.MINE_NAME_ID);

        TextView mine_name_textview = (TextView) findViewById(R.id.nameText);
        TextView neutralization_textview = (TextView) findViewById(R.id.neutralization);
        TextView disarming_textview = (TextView) findViewById(R.id.disarming);
        TextView placement_textview = (TextView) findViewById(R.id.placement);

        if (mine_name!=null)
            mine_name_textview.setText(mine_name);

        if (neutralization!=null)
            neutralization_textview.setText(neutralization);

        if (disarming!=null){
            disarming_textview.setText(disarming);
        }

        if (placement!=null)
            placement_textview.setText(placement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
