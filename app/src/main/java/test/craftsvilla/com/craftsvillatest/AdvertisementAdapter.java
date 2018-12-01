package test.craftsvilla.com.craftsvillatest;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by macbookpro on 01/11/18.
 */

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.MyViewHolder> {

    private final Activity mCtx;
    private ArrayList<Advertise> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.colorView);

        }
    }

    public AdvertisementAdapter(ArrayList<Advertise> data, Activity ctx) {
        this.dataSet = data;
        this.mCtx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advertise_item_view, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        // TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;
        if (listPosition == 0) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.add_one));
        }
        if (listPosition == 1) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.add_two));
        }
        if (listPosition == 2) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.add_three));
        }
        if (listPosition == 3) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.add_four));
        }
        if (listPosition == 4) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, R.drawable.add_five));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Advertise> advertiseList = new ArrayList<Advertise>();

                advertiseList.add(0, new Advertise("New Add", "", "Stri-Fried Chilli Paneer Quinoa Brown Rice","A fresh and unaged cheese that Indians love experimenting with, is our iconic, yet humble, paneer. This peppery, spicy, and tangy chilli-infused dish is popular on our list of starters and appetizers. When served with a brown-rice-quinoa fried-rice, this Indo-Chinese combo packs a mean, healthy punch. Serves 1.",1,250,100,30,"1"));
                advertiseList.add(1, new Advertise("New Add", "", "Chilli Paneer ‘n’ Veg Fried Rice","Everyone claims they invented chilli paneer, but this could be because it’s just so simple and so good. The idea of combining bell peppers with paneer in a fiery sauce is an idea that works every single time. This time around, we’re pairing the deliciousness with a hearty vegetable fried rice. Cause sometimes… familiar food is where the heart’s at, right? Serves 1.",1,250,100,30,"1"));
                advertiseList.add(2, new Advertise("New Add", "", "Chilli Paneer ‘n’ Veg Fried Rice","Everyone claims they invented chilli paneer, but this could be because it’s just so simple and so good. The idea of combining bell peppers with paneer in a fiery sauce is an idea that works every single time. This time around, we’re pairing the deliciousness with a hearty vegetable fried rice. Cause sometimes… familiar food is where the heart’s at, right? Serves 1.",1,250,100,30,"1"));
                advertiseList.add(3, new Advertise("New Add", "", "Olive Oyl Veggie Burrito","From the Israelites of yore, to the new age Greeks and Romans, Olive Oil has made a delightful cul-inary impact and is known to enhance the traditional flavours of the food it infuses. This is why we haven’t shied away from adding this luscious oil to a delicious sour cream amid a bowl hosting some chunky paneer flavoured in peri peri spice, an exotic charred corn salsa made with cajun spice, sweet and tangy jalapeño and berries rice, and a simple spiced tomato curry. This is going to be hard one to ignore!",1,250,100,30,"1"));
                advertiseList.add(4, new Advertise("New Add", "", "Olive Oyl Veggie Burrito","From the Israelites of yore, to the new age Greeks and Romans, Olive Oil has made a delightful cul-inary impact and is known to enhance the traditional flavours of the food it infuses. This is why we haven’t shied away from adding this luscious oil to a delicious sour cream amid a bowl hosting some chunky paneer flavoured in peri peri spice, an exotic charred corn salsa made with cajun spice, sweet and tangy jalapeño and berries rice, and a simple spiced tomato curry. This is going to be hard one to ignore!",1,250,100,30,"1"));
                advertiseList.add(5, new Advertise("New Add", "", "Mughlai Paneer 'n' Nutty Pulao","Ah! An authentic desi treat we’ve all been waiting for has come at last. Not only is this dish made with paneer, an Indian favourite, but Mughlai paneer takes the cream (literally) of paneer variants on our list! Soft cubes of paneer are added into a thick, creamy gravy, made with spices and coated with almond seeds and pomegranate flakes. It doesn’t stop there! Made to savour with an exquisite dish like this is a flavoursome nutty pulao, complete with cashew, raisins, salt and pepper. Dine just like the (Mughal) kings with this one!",1,250,100,30,"1"));
                advertiseList.add(6, new Advertise("New Add", "", "Mexican Burrito Bento","Brown rice tossed in a coriander-mint chutney, cooked with onions and vibrant bell peppers, spicy corn salsa, and a grilled veggie wrap which is both crunchy and hearty; we call it Mexico in a box! Dig into this delish meal whenever you're happy, sad or just bored, and it will do a great job lifting your spirits. What's not to like! Cheeky chutney rice, good. Corn salsa, good. Grilled veggie wrap, good. Order already!",1,250,100,30,"1"));

                Intent i=new Intent(mCtx,AllMenuActivity.class);
                i.putExtra("list",advertiseList);
                mCtx.startActivity(i);

            }
        });

//
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

