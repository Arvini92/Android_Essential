package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private List<Product> products;

    public ProductListAdapter(Context context,
                              int resource,
                              List<Product> objects) {


        super(context, resource, objects);
        products = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Product product = products.get(position);

        TextView nameText = convertView.findViewById(R.id.nameText);
        nameText.setText(product.getName());

        NumberFormat formater = NumberFormat.getCurrencyInstance();
        String price = formater.format(product.getPrice());
        TextView priceText = convertView.findViewById(R.id.priceText);
        priceText.setText(price);

        ImageView iv = convertView.findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManger = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManger.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
