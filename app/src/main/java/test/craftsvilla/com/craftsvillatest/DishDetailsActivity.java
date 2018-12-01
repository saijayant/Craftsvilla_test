package test.craftsvilla.com.craftsvillatest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by macbookpro on 03/11/18.
 */

public class DishDetailsActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Button online;
    int listPosition = 0;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;
    @SuppressWarnings("unchecked")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
        setContentView(R.layout.dish_details);


        ArrayList<Advertise> dataSet = (ArrayList<Advertise>) getIntent().getSerializableExtra("list");


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        online = (Button) findViewById(R.id.online);


        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
                StringBuilder sb = new StringBuilder(32);
                Random random = new Random();
                for (int i = 0; i < 32; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
                }

                dbHelper = new DbHelper(DishDetailsActivity.this);

                String output = sb.toString();

                sqLiteDatabase = dbHelper.getWritableDatabase();

                dbHelper.insertData(sqLiteDatabase,
                        dataSet.get(listPosition).getAdvertise(),
                        dataSet.get(listPosition).getAd_image(),
                        dataSet.get(listPosition).getName(),
                        dataSet.get(listPosition).getDescription(),
                        dataSet.get(listPosition).getPrice(),
                        dataSet.get(listPosition).getTax(),
                        dataSet.get(listPosition).getDeliverfee(),
                        dataSet.get(listPosition).getQuantity(),
                        output);

                Toast.makeText(DishDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(DishDetailsActivity.this,AddToCartActivity.class);
                startActivity(i);

            }
        });


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScroll = appBarLayout.getTotalScrollRange();
                int currentScroll = totalScroll + verticalOffset;
                int scrollRange = -1;
                boolean isShow = false;
                if ((currentScroll) < 50) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                        collapsingToolbarLayout.setTitle("Dish Details Activity");
                        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window w = getWindow();
                        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                        collapsingToolbarLayout.setTitle("Chicken Ham Sandwich");

                    }
                }
            }
        });


        YouTubePlayerView youtubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youtubePlayerView);

        youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = "F7ttFw4oBMw";
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);


    }


}