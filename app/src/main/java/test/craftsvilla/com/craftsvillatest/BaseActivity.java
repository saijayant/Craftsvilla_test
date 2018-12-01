package test.craftsvilla.com.craftsvillatest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    private RecyclerView advertise, dishList, restroList;
    private ImageView cart;
    private Animation animShake;
    TextView items;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        cart = (ImageView) findViewById(R.id.cart);
        items = (TextView) findViewById(R.id.items);


        if (getContentViewId() == R.layout.activity_home) {

            advertise = (RecyclerView) findViewById(R.id.advertise);
            dishList = (RecyclerView) findViewById(R.id.dishList);
            restroList = (RecyclerView) findViewById(R.id.restroList);


            animShake = AnimationUtils.loadAnimation(BaseActivity.this, R.anim.shake);


            ArrayList<Advertise> advertiseList = new ArrayList<Advertise>();

            advertiseList.add(0, new Advertise("New Add", "", "Stri-Fried Chilli Paneer Quinoa Brown Rice", "A fresh and unaged cheese that Indians love experimenting with, is our iconic, yet humble, paneer. This peppery, spicy, and tangy chilli-infused dish is popular on our list of starters and appetizers. When served with a brown-rice-quinoa fried-rice, this Indo-Chinese combo packs a mean, healthy punch. Serves 1.", 1, 250, 100, 30, "1"));
            advertiseList.add(1, new Advertise("New Add", "", "Chilli Paneer ‘n’ Veg Fried Rice", "Everyone claims they invented chilli paneer, but this could be because it’s just so simple and so good. The idea of combining bell peppers with paneer in a fiery sauce is an idea that works every single time. This time around, we’re pairing the deliciousness with a hearty vegetable fried rice. Cause sometimes… familiar food is where the heart’s at, right? Serves 1.", 1, 250, 100, 30, "1"));
            advertiseList.add(2, new Advertise("New Add", "", "Chilli Paneer ‘n’ Veg Fried Rice", "Everyone claims they invented chilli paneer, but this could be because it’s just so simple and so good. The idea of combining bell peppers with paneer in a fiery sauce is an idea that works every single time. This time around, we’re pairing the deliciousness with a hearty vegetable fried rice. Cause sometimes… familiar food is where the heart’s at, right? Serves 1.", 1, 250, 100, 30, "1"));
            advertiseList.add(3, new Advertise("New Add", "", "Olive Oyl Veggie Burrito", "From the Israelites of yore, to the new age Greeks and Romans, Olive Oil has made a delightful cul-inary impact and is known to enhance the traditional flavours of the food it infuses. This is why we haven’t shied away from adding this luscious oil to a delicious sour cream amid a bowl hosting some chunky paneer flavoured in peri peri spice, an exotic charred corn salsa made with cajun spice, sweet and tangy jalapeño and berries rice, and a simple spiced tomato curry. This is going to be hard one to ignore!", 1, 250, 100, 30, "1"));
            advertiseList.add(4, new Advertise("New Add", "", "Olive Oyl Veggie Burrito", "From the Israelites of yore, to the new age Greeks and Romans, Olive Oil has made a delightful cul-inary impact and is known to enhance the traditional flavours of the food it infuses. This is why we haven’t shied away from adding this luscious oil to a delicious sour cream amid a bowl hosting some chunky paneer flavoured in peri peri spice, an exotic charred corn salsa made with cajun spice, sweet and tangy jalapeño and berries rice, and a simple spiced tomato curry. This is going to be hard one to ignore!", 1, 250, 100, 30, "1"));
            advertiseList.add(5, new Advertise("New Add", "", "Mughlai Paneer 'n' Nutty Pulao", "Ah! An authentic desi treat we’ve all been waiting for has come at last. Not only is this dish made with paneer, an Indian favourite, but Mughlai paneer takes the cream (literally) of paneer variants on our list! Soft cubes of paneer are added into a thick, creamy gravy, made with spices and coated with almond seeds and pomegranate flakes. It doesn’t stop there! Made to savour with an exquisite dish like this is a flavoursome nutty pulao, complete with cashew, raisins, salt and pepper. Dine just like the (Mughal) kings with this one!", 1, 250, 100, 30, "1"));
            advertiseList.add(6, new Advertise("New Add", "", "Mexican Burrito Bento", "Brown rice tossed in a coriander-mint chutney, cooked with onions and vibrant bell peppers, spicy corn salsa, and a grilled veggie wrap which is both crunchy and hearty; we call it Mexico in a box! Dig into this delish meal whenever you're happy, sad or just bored, and it will do a great job lifting your spirits. What's not to like! Cheeky chutney rice, good. Corn salsa, good. Grilled veggie wrap, good. Order already!", 1, 250, 100, 30, "1"));



           updatedData();



            AdvertisementAdapter horizontalAdapter = new AdvertisementAdapter(advertiseList, BaseActivity.this);
            RecomendedDishesAdapter recDishAdapter = new RecomendedDishesAdapter(advertiseList, BaseActivity.this, "base", new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position, ArrayList<Advertise> list) {

                    updatedData();
                    cart.startAnimation(animShake);

                }
            });
            HandpickedRestroAdapter handpickedAdapter = new HandpickedRestroAdapter(advertiseList, BaseActivity.this);

            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(BaseActivity.this, LinearLayoutManager.HORIZONTAL, false);

            advertise.setLayoutManager(horizontalLayoutManagaer);
            advertise.setAdapter(horizontalAdapter);
            advertise.setNestedScrollingEnabled(false);

            LinearLayoutManager horizontalLayoutManagaer1
                    = new LinearLayoutManager(BaseActivity.this, LinearLayoutManager.HORIZONTAL, false);

            dishList.setLayoutManager(horizontalLayoutManagaer1);
            dishList.setAdapter(recDishAdapter);
            dishList.setNestedScrollingEnabled(false);


            restroList.setLayoutManager(new GridLayoutManager(this, 2));
            restroList.setAdapter(handpickedAdapter);
            restroList.setNestedScrollingEnabled(false);


            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BaseActivity.this, AddToCartActivity.class);
                    startActivity(i);
                }
            });

        }

    }

    private void updatedData() {
        dbHelper = new DbHelper(this);

        sqLiteDatabase = dbHelper.getWritableDatabase();

        ArrayList<Advertise> cor = dbHelper.GetAllData(sqLiteDatabase);
        int subt = 0;

        for (int i = 0; i < cor.size(); i++) {
            subt = subt + cor.get(i).getQuantity();
        }
        try {
            items.setText(" " + subt + " ");
        }catch (Exception e){

        }
    }


    @Override
    protected void onResume() {
        updatedData();



        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.navigation_dashboard) {
                startActivity(new Intent(this, DashboardActivity.class));
            } else if (itemId == R.id.navigation_notifications) {
                startActivity(new Intent(this, NotificationsActivity.class));
            }
            finish();
        }, 300);
        return true;
    }

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
