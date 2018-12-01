package test.craftsvilla.com.craftsvillatest;

import java.io.Serializable;

/**
 * Created by macbookpro on 01/11/18.
 */

public class Advertise implements Serializable {

    String Advertise;
    String ad_image;
    String name;
    String description;
    int quantity;
    int price;
    int tax;
    int deliverfee;
    String id;

    public String getAdvertise() {
        return Advertise;
    }

    public void setAdvertise(String advertise) {
        Advertise = advertise;
    }

    public String getAd_image() {
        return ad_image;
    }

    public void setAd_image(String ad_image) {
        this.ad_image = ad_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDeliverfee() {
        return deliverfee;
    }

    public void setDeliverfee(int deliverfee) {
        this.deliverfee = deliverfee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Advertise(String addverstise, String image_url, String name,String description,int quatity,int price,int tax,int deliveryfee,String id) {

        this.Advertise=addverstise;
        this.ad_image=image_url;
        this.name=name;
        this.description=description;
        this.quantity=quatity;
        this.price=price;
        this.tax=tax;
        this.deliverfee=deliveryfee;
        this.id=id;
    }
}
