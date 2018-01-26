package fr.parisnanterre.pmoo.androidm2td1.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

import fr.parisnanterre.pmoo.androidm2td1.adapter.FilmAdapter;
import fr.parisnanterre.pmoo.androidm2td1.model.Film;


public class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    private WeakReference<Film> film;
    private final WeakReference<Activity> activity;
    private final WeakReference<FilmAdapter> adapter;
    private String test;
    public DownloadImage(Activity activity, FilmAdapter adapter, Film film) {
        this.activity = new WeakReference<>(activity);
        this.adapter = new WeakReference<>(adapter);
        this.film = new WeakReference<>(film);
        this.test = "NO";
    }

    @Override
    protected Bitmap doInBackground(String... URLS) {
        if (URLS.length > 1)
            this.test = URLS[1];
        Film filmSR = this.film.get();
        if ( filmSR != null) {
            Log.i("DEBUG", "doInBackground, DOWNLOADING, film: " + filmSR.getTitle());
            try {
                URL url = new URL(URLS[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                Log.i("EXCEPTION", String.format("doInBackGround, film: %s, error: %s", filmSR.getTitle(), e.getMessage()));
                return null;
            }
        }
        Log.i("DEBUG", "doInBackground, DOWNLOAD CANCEL, film = null");
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Activity activity = this.activity.get();
        ArrayAdapter adapter = this.adapter.get();
        Film film = this.film.get();
        if (activity != null && adapter != null && film != null) {
            Log.i("DEBUG", String.format("onPostExecute, SUCCESS, film: %s", film.getTitle()));
            //TODO Il vaut mieux faire l'affectation d'image en dehors du thread UI donc dans la
            //TODO méthode doInBackGround
            if (this.test.equals("YES")) {
                film.update();
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(film.bImage);
                film.image = BitmapFactory.decodeStream(arrayInputStream);
                Log.d("DEBUG", "YES UPDATE");
                adapter.notifyDataSetChanged();
//                StoreFilm task = new StoreFilm(film, adapter);
//                task.execute();
            } else {
                film.setImage(bitmap);
                adapter.notifyDataSetChanged();
            }
        } else {
            Log.i("DEBUG", "onPostExecute, CANCEL");
        }
    }
}
