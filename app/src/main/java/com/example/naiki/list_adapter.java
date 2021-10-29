package com.example.naiki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class list_adapter extends ArrayAdapter<String> {

//         list_adapter(Context context, ArrayList<list_class> list_classArrayList){
//

    Context context;
    String it;
    String dt;
    String im;

    public list_adapter(@NonNull Context c, String it, String dt, String im) {
        super(c , R.layout.list_row, R.id.item_name_id);
        this.it = it;
        this.dt = dt;
        this.im = im;
    }



    TextView t1, t2;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.list_row,parent,false);

        ImageView img=row.findViewById(R.id.item_image);
        TextView tv1=row.findViewById(R.id.item_name_id);
        TextView tv2=row.findViewById(R.id.item_details);


        tv1.setText(it);
        tv2.setText(dt);

        String url= im;


        class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
            private String url;
            private ImageView imageView;

            public ImageLoadTask(String url, ImageView imageView) {
                this.url = url;
                this.imageView = imageView;
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    URL connection = new URL(url);
                    InputStream input = connection.openStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 400, 400, true);
                    return resized;
                } catch (Exception e) {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                imageView.setImageBitmap(result);
            }
        }
        ImageLoadTask obj=new ImageLoadTask(url,img);
        obj.execute();

        return row;


//             list_class list_class = getItem(position);
//
//             if (convertView == null)
//             {
//                 convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row,parent, false);
//             }
//
//        ImageView imageView = convertView.findViewById(R.id.item_image);
//             t1 = convertView.findViewById(R.id.item_name_id);
//             t2 = convertView.findViewById(R.id.item_details);
//
////             imageView.setImageResource();
//             t1.setText(list_class.item_name);
//             t2.setText(list_class.item_details);
//             return super.getView(position, convertView, parent);
    }
}
