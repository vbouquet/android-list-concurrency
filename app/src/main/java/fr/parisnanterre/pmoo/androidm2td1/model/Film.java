package fr.parisnanterre.pmoo.androidm2td1.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class Film extends SugarRecord {
    public String title;
    public String production;
    public Date date;
    public byte[] bImage;
    @Ignore
    public Bitmap image;

    public Film() {

    }

    public Film(@NonNull String title, @NonNull String production, @NonNull Date date, Bitmap image) {
        this.title      = title;
        this.production = production;
        this.image      = image;
        this.date       = date;
    }

    public Film(@NonNull Film film) {
        this.title      = film.title;
        this.production = film.production;
        this.date       = film.date;
        this.image      = film.image;
    }

    public @NonNull String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public @NonNull String getProduction() {
        return production;
    }

    public void setProduction(@NonNull String production) {
        this.production = production;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public @NonNull Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public String getPrettyDate() {
        return DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(this.date);
    }
}
