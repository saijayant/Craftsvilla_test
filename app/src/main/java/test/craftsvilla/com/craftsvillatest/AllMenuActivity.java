package test.craftsvilla.com.craftsvillatest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AllMenuActivity extends AppCompatActivity {

    RecyclerView cartList;
    TextView subtotal, delivery, total, discount;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;
    private ImageView cart;
    private Animation animShake;
    TextView items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        setContentView(R.layout.all_menu);

        cartList = findViewById(R.id.cartList);

        cart = (ImageView) findViewById(R.id.cart);
        items = (TextView) findViewById(R.id.items);
        animShake = AnimationUtils.loadAnimation(AllMenuActivity.this, R.anim.shake);


        updatedData();


        ArrayList<Advertise> dataSet = (ArrayList<Advertise>) getIntent().getSerializableExtra("list");


        RecomendedDishesAdapter recDishAdapter = new RecomendedDishesAdapter(dataSet, AllMenuActivity.this, "allmenu", new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position, ArrayList<Advertise> list) {
                updatedData();
                cart.startAnimation(animShake);

            }
        });

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(AllMenuActivity.this, LinearLayoutManager.VERTICAL, false);

        cartList.setLayoutManager(horizontalLayoutManagaer);
        cartList.setAdapter(recDishAdapter);
        cartList.setNestedScrollingEnabled(false);

         cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllMenuActivity.this, AddToCartActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        updatedData();
        super.onResume();
    }

    private void updatedData() {
        dbHelper = new DbHelper(this);

        sqLiteDatabase = dbHelper.getWritableDatabase();

        ArrayList<Advertise> cor = dbHelper.GetAllData(sqLiteDatabase);
        int subt = 0;

        for (int i = 0; i < cor.size(); i++) {
            subt = subt + cor.get(i).getQuantity();
        }
        items.setText(" "+subt+" ");
    }

}
