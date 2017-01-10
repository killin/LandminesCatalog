package mapps.landmines;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mapps.landmines.model.MineModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MineListAdapter extends ArrayAdapter<MineModel>{

    public static final String TAG="MineListAdapter";

    private List<MineModel> mineModels;
    ArrayList<MineModel> arraylist;

    Context context;

    public MineListAdapter(Context context, int resource, List<MineModel> objects) {
        super(context, resource, objects);
        this.mineModels = objects;
        this.context = context;
        arraylist = new ArrayList<MineModel>();
        arraylist.addAll(mineModels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false);
        }

        MineModel mineModel = mineModels.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(mineModel.getName());

        TextView originView = (TextView) convertView.findViewById(R.id.countryOfOrigin);
        originView.setText(mineModel.getCountryOrigin());

        TextView usedView = (TextView) convertView.findViewById(R.id.countriesUsed);
        usedView.setText(mineModel.getCountriesUsed());

        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);

        try {
            //Log.d(TAG, "filename: " + mineModel.getImageFilename());
            Picasso.with(convertView.getContext()).load("file:///android_asset/" + mineModel.getImageFilename() + ".jpg").into(iv);
        }catch (Exception e){
            e.getMessage();
        }

        return convertView;
    }


    private List<MineModel> searchString(List<MineModel> all, String searchString){

        List<MineModel> result= new ArrayList<MineModel>();

        for (MineModel mine:
                all) {
            if (mine.getName().contains(searchString.trim().toUpperCase())){
                result.add(mine);
                Log.d(TAG, "adding to searched list:" + mine.getName());
            }
        }

        return result;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        mineModels.clear();
        if (charText.length() == 0) {
            mineModels.addAll(arraylist);

        } else {
            for (MineModel model : arraylist) {
                if (charText.length() != 0 && model.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mineModels.add(model);
                }

                else if (charText.length() != 0 && model.getCountryOrigin().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mineModels.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


    private Bitmap getBitmapFromAsset(String imageFilename) {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(imageFilename + ".jpg");
            Log.d(TAG, "Found: " + imageFilename);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            Log.d(TAG, "Not Found: " + imageFilename);
            e.printStackTrace();
            return null;
        }
    }

}
