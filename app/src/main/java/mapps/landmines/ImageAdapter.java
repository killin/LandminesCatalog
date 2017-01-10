package mapps.landmines;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by RK on 10/6/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private static final String TAG = "ImageAdapter";
    private Context mContext;

    String[] images;

    public ImageAdapter(Context mContext, String[] images) {

        this.mContext = mContext;
        this.images = images;

        for (String str : images
                ) {
            Log.d(TAG, "image :" + str);
        }

    }

    @Override
    public int getCount() {
        Log.d(TAG, "images.length :" + images.length);
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final String filename = images[position].trim();

        ImageView imageView;

        if (convertView == null) {

            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_image, null);
        }
        //Log.d(TAG, "filename: " + filename + ", position: " + position);
        imageView = (ImageView) convertView.findViewById(R.id.imageViewForGrid);
        Picasso.with(convertView.getContext())
                .load("file:///android_asset/" + filename + ".jpg")
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "image not found ****: " + filename );
                    }
                });


        return convertView;

    }


}
