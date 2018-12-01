package test.craftsvilla.com.craftsvillatest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by macbookpro on 01/11/18.
 */

public class CheckoutItemAdapter extends RecyclerView.Adapter<CheckoutItemAdapter.MyViewHolder> {

    private final Activity mCtx;
    private ArrayList<Advertise> dataSet;
    CustomItemClickListener listner;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;

    public CheckoutItemAdapter(ArrayList<Advertise> data, Activity ctx, CustomItemClickListener clicked) {
        this.dataSet = data;
        this.mCtx = ctx;
        this.listner = clicked;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
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

        holder.priceoriginal.setText(("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(dataSet.get(listPosition).getPrice() * dataSet.get(listPosition).getQuantity())));

//        holder.price_cross.setText(("\u20B9" + NumberFormat.getNumberInstance(Locale.getDefault()).format(dataSet.get(listPosition).getPrice()+100)));

//        holder.price_cross.setPaintFlags(holder.price_cross.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mCtx, DishDetailsActivity.class);
                mCtx.startActivity(i);
            }
        });

        holder.button.setNumber(String.valueOf(dataSet.get(listPosition).getQuantity()));

        holder.button.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {


                String num = holder.button.getNumber();
                dataSet.get(listPosition).setQuantity(Integer.valueOf(num));

                dbHelper = new DbHelper(mCtx);

                sqLiteDatabase = dbHelper.getWritableDatabase();


                dbHelper.DeleteRecords(sqLiteDatabase,dataSet.get(listPosition).getId() );
                dbHelper.insertData(sqLiteDatabase,
                        dataSet.get(listPosition).getAdvertise(),
                        dataSet.get(listPosition).getAd_image(),
                        dataSet.get(listPosition).getName(),
                        dataSet.get(listPosition).getDescription(),
                        dataSet.get(listPosition).getPrice(),
                        dataSet.get(listPosition).getTax(),
                        dataSet.get(listPosition).getDeliverfee(),
                        dataSet.get(listPosition).getQuantity(),
                        dataSet.get(listPosition).getId() );


                listner.onItemClick(view, listPosition, dataSet);


                notifyDataSetChanged();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper = new DbHelper(mCtx);
                sqLiteDatabase = dbHelper.getWritableDatabase();
                dbHelper.DeleteRecords(sqLiteDatabase, dataSet.get(listPosition).getId());

                dataSet.remove(dataSet.get(listPosition));


                listner.onItemClick(v, listPosition, dataSet);


                notifyItemRemoved(listPosition);

                notifyDataSetChanged();




            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageViewIcon, delete;
        TextView name, price_cross, price_original, priceoriginal, add;
        ElegantNumberButton button;

        public MyViewHolder(View itemView) {
            super(itemView);
            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.colorView);
//            price_cross = (TextView) itemView.findViewById(R.id.price_cross);
            priceoriginal = (TextView) itemView.findViewById(R.id.price_original);
            name = (TextView) itemView.findViewById(R.id.name);
            add = (TextView) itemView.findViewById(R.id.add);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            button = (ElegantNumberButton) itemView.findViewById(R.id.number_button);


        }
    }
}

