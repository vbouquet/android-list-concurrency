package fr.parisnanterre.pmoo.androidm2td1.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

import fr.parisnanterre.pmoo.androidm2td1.adapter.FilmAdapter;
import fr.parisnanterre.pmoo.androidm2td1.model.Film;


public class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    private WeakReference<Film> film;
    private final WeakReference<Activity> activity;
    private final WeakReference<FilmAdapter> adapter;

    public DownloadImage(Activity activity, FilmAdapter adapter, Film film) {
        this.activity = new WeakReference<>(activity);
        this.adapter = new WeakReference<>(adapter);
        this.film = new WeakReference<>(film);
    }

    @Override
    protected Bitmap doInBackground(String... URLS) {
        if (film.get() != null) {
            Log.i("DEBUG", "doInBackground, SUCCESS, film: " + film.get().getTitle());
            try {
                URL url = new URL(URLS[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                Log.i("EXCEPTION", String.format("doInBackGround, film: %s, error: %s", film.get().getTitle(), e.getMessage()));
                return null;
            }
        }
        Log.i("DEBUG", "doInBackground, CANCEL, film = null");
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (this.activity.get() != null && this.adapter.get() != null && this.film.get() != null) {
            Log.i("DEBUG", String.format("onPostExecute, SUCCESS, film: %s", film.get().getTitle()));
            film.get().setImage(bitmap);
            adapter.get().notifyDataSetChanged();
        } else {
            Log.i("DEBUG", "onPostExecute, CANCEL");
        }
    }
}
