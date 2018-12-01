package test.craftsvilla.com.craftsvillatest;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by macbookpro on 01/11/18.
 */

public class RecomendedDishesAdapter extends RecyclerView.Adapter<RecomendedDishesAdapter.MyViewHolder> {

    private final Activity mCtx;
    private ArrayList<Advertise> dataSet;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;
    private String from;
    CustomItemClickListener listner;



    public RecomendedDishesAdapter(ArrayList<Advertise> dataSet, Activity allMenuActivity, String allmenu, CustomItemClickListener customItemClickListener) {
        this.dataSet = dataSet;
        this.mCtx = allMenuActivity;
        this.from = allmenu;
        this.listner=customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recomended_dishes_for_you, parent, false);

        View view1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recomended_dishes_for_you_two, parent, false);


        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder=null;
        if(from.equalsIgnoreCase("allmenu")) {
             myViewHolder = new MyViewHolder(view1);
        }else{
             myViewHolder = new MyViewHolder(view);

        }
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        // TextView textViewVersion = holder.textViewVersion;

        if (listPosition == 0) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_one));
        }
        if (listPosition == 1) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_two));
        }
        if (listPosition == 2) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_three));
        }
        if (listPosition == 3) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_four));
        }
        if (listPosition == 4) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_two));
        }
        if (listPosition == 5) {
            holder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.dish_one));
        }

        holder.name.setText(dataSet.get(listPosition).getName());

        holder.priceoriginal.setText(("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(dataSet.get(listPosition).getPrice())));

        holder.price_cross.setText(("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(dataSet.get(listPosition).getPrice()+100)));

        holder.price_cross.setPaintFlags(holder.price_cross.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mCtx,DishDetailsActivity.class);
                ArrayList<Advertise> a =new ArrayList<>();

                a.add(0,dataSet.get(listPosition));

                i.putExtra("list",a);
                i.putExtra("from","list");
                mCtx.startActivity(i);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper = new DbHelper(mCtx);
                char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
                StringBuilder sb = new StringBuilder(32);
                Random random = new Random();
                for (int i = 0; i < 32; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
                }
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
                        output );

                listner.onItemClick(v,listPosition,dataSet);

                Toast.makeText(mCtx, "Added to cart", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageViewIcon;
        TextView name,price_cross,price_original,priceoriginal;
        Button add;

        public MyViewHolder(View itemView) {
            super(itemView);
            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            imageViewIcon= (ImageView) itemView.findViewById(R.id.colorView);
            price_cross = (TextView) itemView.findViewById(R.id.price_cross);
            priceoriginal = (TextView) itemView.findViewById(R.id.price_original);
            name = (TextView) itemView.findViewById(R.id.name);
            add = (Button) itemView.findViewById(R.id.add);

        }
    }
}

