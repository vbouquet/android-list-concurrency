package fr.parisnanterre.pmoo.androidm2td1.task;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import android.os.Handler;
import android.widget.ArrayAdapter;

import fr.parisnanterre.pmoo.androidm2td1.adapter.FilmAdapter;
import fr.parisnanterre.pmoo.androidm2td1.model.Film;


public class DownloadImageThread implements Runnable {

    private WeakReference<Film> film;
    private final WeakReference<Activity> activity;
    private final WeakReference<FilmAdapter> adapter;
    private final String url;
    private final Handler handler;

    public DownloadImageThread(Activity activity, FilmAdapter adapter, Film film, String url) {
        this.activity = new WeakReference<>(activity);
        this.adapter = new WeakReference<>(adapter);
        this.film = new WeakReference<>(film);
        this.url = url;
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Update adapter on main UI thread
     */
    private void updateAdapter() {
        final ArrayAdapter adapter = this.adapter.get();
        final Activity activity = this.activity.get();
        if (activity != null && adapter != null && this.film.get() != null) {
            // 2 options: call runOnUiThread or use handler.post(...)
            /*
            this.activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            */
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("DEBUG", "run, updateApter OK");
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            Log.i("DEBUG", "run, updateApter CANCEL");
        }
    }

    @Override
    public void run() {
        Film film = this.film.get();
        if (film != null) {
            try {
                URL url = new URL(this.url);
                if (this.activity.get() != null && this.adapter.get() != null) {
                    Log.i("DEBUG", "run, DOWNLOADING, BEGIN, film: " + film.getTitle());
                    film.setImage(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                    Log.i("DEBUG", "run, DOWNLOADING, END, film: " + film.getTitle());
                    updateAdapter();
                } else {
                    Log.i("DEBUG", "run, DOWNLOADING, CANCEL");
                }
            } catch (IOException e) {
                Log.i("EXCEPTION", String.format("run, DOWNLOADING, film: %s, error: %s", film.getTitle(), e.getMessage()));
            }
        } else {
            Log.i("DEBUG", "run, CANCEL, film = null");
        }
    }
}
