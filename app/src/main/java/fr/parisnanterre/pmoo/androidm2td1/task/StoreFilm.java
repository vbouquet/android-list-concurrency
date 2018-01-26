package fr.parisnanterre.pmoo.androidm2td1.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.ByteArrayOutputStream;

import fr.parisnanterre.pmoo.androidm2td1.model.Film;

public class StoreFilm extends AsyncTask<Void, Void, String> {
    private Film film;
    private ArrayAdapter adapter;

    public StoreFilm(Film film, ArrayAdapter adapter) {
        this.film = film;
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (adapter == null && film.image != null) {
            Log.d("DEBUG", String.format("Film, compressing image to byte array = %s", this.film.title));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            film.image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            film.bImage = stream.toByteArray();
        }
        if (adapter != null) {
            film.update();
            return "YES";
        } else {
            film.save();
        }
        Log.d("DEBUG", String.format("Update film = %s", this.film.title));
        film.bImage = null;
        return "NO";
    }

    @Override
    protected void onPostExecute(String strings) {
        if (strings.equals("YES"))
            adapter.notifyDataSetChanged();
    }
}
