package test.craftsvilla.com.craftsvillatest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AddToCartActivity extends AppCompatActivity {

    RecyclerView cartList;
    TextView subtotal, delivery, total, discount;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        setContentView(R.layout.add_to_cart_activity);

        initViewAndData();

        dbHelper = new DbHelper(this);

        sqLiteDatabase = dbHelper.getWritableDatabase();

        ArrayList<Advertise> cor = dbHelper.GetAllData(sqLiteDatabase);

        CheckoutItemAdapter horizontalAdapter = new CheckoutItemAdapter(cor, this, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position, ArrayList<Advertise> list) {

                getCalculateTotal(list);

            }
        });

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        cartList.setLayoutManager(horizontalLayoutManagaer);
        cartList.setAdapter(horizontalAdapter);
        cartList.setNestedScrollingEnabled(false);

        getCalculateTotal(cor);

    }

    private void initViewAndData() {
        cartList = findViewById(R.id.cartList);
        subtotal = findViewById(R.id.subtotal);
        delivery = findViewById(R.id.delivery);
        discount = findViewById(R.id.discount);
        total = findViewById(R.id.total);


    }

    private void getCalculateTotal(ArrayList<Advertise> list) {


        if (list.size() > 0) {
            int subt = 0;

            for (int i = 0; i < list.size(); i++) {
                subt = subt + (list.get(i).getPrice() * list.get(i).getQuantity());
            }

            subtotal.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(subt));
            delivery.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(100));
            discount.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(00));

            int tot = subt + 100 + 00;

            total.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(tot));
        } else {
            subtotal.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(0000));
            delivery.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(000));
            discount.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(00));
            total.setText("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(00));
            Toast.makeText(this, "Nothing in cart", Toast.LENGTH_SHORT).show();


        }


    }
}
