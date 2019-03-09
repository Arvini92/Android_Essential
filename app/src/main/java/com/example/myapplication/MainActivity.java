package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";
    private static final int MENU_ITEM_LOGOUT = 1001;
    public static final String PRODUCT_ID = "PRODUCT_ID";

    private static final int DETAIL_REQUEST = 1111;
    public static final String RETURN_MESSAGE = "RETURN_MESSAGE";

    public static String webUrl = "https://sfw.so/";
    public static String email = "summinii92@gmail.com";

    private List<Product> products = DataProvider.productList;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText et = findViewById(R.id.editText);
//                String entry = et.getText().toString();
//
//
//                Snackbar.make(view, "You entered: " + entry, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                
                String[] adresses = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, adresses);
                intent.putExtra(Intent.EXTRA_TEXT, "Please send some information");
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });





        Gson gson = new Gson();

//        LinearLayout layout = findViewById(R.id.button);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        for (int i = 0; i < 3; i++) {
            Button button = new Button(this);
            button.setText("Click me");
            button.setLayoutParams(params);
//            layout.addView(button);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
//            builder.append(getString(R.string.lorem))
        }

        Log.d(LOG_TAG, "onCreate");

        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.editText);
                String entry = et.getText().toString();


                Snackbar.make(v, "You entered: " + entry, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView iv = findViewById(R.id.photo);
//        iv.setImageResource(R.drawable.jacket101);

        String imageName = "jacket101";
        int res = getResources().getIdentifier(imageName, "drawable", getPackageName());
        iv.setImageResource(res);

//        try {
//            InputStream stream = getAssets().open(imageName + "png");
//            Drawable d = Drawable.createFromStream(stream, null);
//            iv.setImageDrawable(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            String[] items = getResources().getStringArray(R.array.clothing);
//            ArrayAdapter<String> adapter =
//                    new ArrayAdapter<>(this,
//                            android.R.layout.simple_list_item_1,
//                            android.R.id.text1, items);

            ProductListAdapter adapter = new ProductListAdapter(
                    this, R.layout.list_item, products
            );
//          ListView lv = findViewById(android.R.id.list); // error null
//            setContentView(R.layout.content_main);
//            ListView lv = (ListView)findViewById(R.id.list);
//          ListView lv = getListView();
//            lv.setAdapter(adapter);

//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//
//                    Product product = products.get(position);
//
//                    intent.putExtra(PRODUCT_ID, product.getProductId());
//
//                    startActivityForResult(intent, DETAIL_REQUEST);
//                }
//            });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(MainActivity.this, "Your orientation is portrait", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "Your orientation is landscape", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add(0, MENU_ITEM_LOGOUT, 102, getString(R.string.logout));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
////            Toast.makeText(this, "You selected the settings menu", Toast.LENGTH_LONG).show();
//            Snackbar.make(coordinatorLayout, "You selected settings", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            return true;
//        }

        switch (id) {
            case  R.id.action_settings:
                Snackbar.make(coordinatorLayout, "You selected settings", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            case  R.id.action_about:
//                Snackbar.make(coordinatorLayout, "You selected about", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case  R.id.action_web:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
                return true;

            case  MENU_ITEM_LOGOUT:
                Snackbar.make(coordinatorLayout, "You selected logout", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            case  R.id.action_cart:
                Snackbar.make(coordinatorLayout, "You selected shopping cart", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonClickHandler(View view) {
        EditText et = findViewById(R.id.editText);
        String name = et.getText().toString();
        Snackbar.make(coordinatorLayout, "You clicked the button", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == DETAIL_REQUEST) {
            if(resultCode == RESULT_OK) {
                String message = data.getStringExtra(RETURN_MESSAGE);
                Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                        .setAction("Go to cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Going to cart", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }
}
