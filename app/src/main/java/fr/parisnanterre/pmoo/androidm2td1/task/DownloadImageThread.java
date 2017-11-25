package fr.parisnanterre.pmoo.androidm2td1.task;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import android.os.Handler;

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
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.get().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void run() {
        if (film.get() != null) {
            Log.i("DEBUG", "doInBackground, SUCCESS, film: " + film.get().getTitle());
            try {
                URL url = new URL(this.url);
                if (this.activity.get() != null && this.adapter.get() != null && this.film.get() != null) {
                    film.get().setImage(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                    updateAdapter();
                }
            } catch (IOException e) {
                Log.i("EXCEPTION", String.format("doInBackGround, film: %s, error: %s", film.get().getTitle(), e.getMessage()));
            }
        } else {
            Log.i("DEBUG", "doInBackground, CANCEL, film = null");
        }
    }
}
