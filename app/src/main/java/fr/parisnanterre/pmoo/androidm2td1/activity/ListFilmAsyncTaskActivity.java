package fr.parisnanterre.pmoo.androidm2td1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.parisnanterre.pmoo.androidm2td1.R;
import fr.parisnanterre.pmoo.androidm2td1.adapter.FilmAdapter;
import fr.parisnanterre.pmoo.androidm2td1.adapter.FilmCollection;
import fr.parisnanterre.pmoo.androidm2td1.model.Film;
import fr.parisnanterre.pmoo.androidm2td1.task.DownloadImage;

public class ListFilmAsyncTaskActivity extends AppCompatActivity {
    private FilmAdapter filmAdapter;
    private static final String URLImage = "https://picsum.photos/100/50?random";
//    private static final String URLImage = "http://lorempixel.com/100/50/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmAdapter = new FilmAdapter(this, new ArrayList<Film>());
        filmAdapter.add(FilmCollection.getRandomFilm());

        final ListView listView = findViewById(R.id.activity_main_listview);
        listView.setAdapter(filmAdapter);

        Button addButton    = findViewById(R.id.activity_main_button_add);
        Button updateButton = findViewById(R.id.activity_main_button_update);
        Button resetButton  = findViewById(R.id.activity_main_button_reset);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("DEBUG", "listView, onLongClick");
                Film film = filmAdapter.getItem(i);
                DownloadImage task = new DownloadImage(ListFilmAsyncTaskActivity.this, filmAdapter, film);
                // Execute task or execute on executor
                // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, URLImage);
                task.execute(URLImage);
                Toast.makeText(getApplicationContext(),
                        String.format("Update image on film: %s", film.title), Toast.LENGTH_SHORT).show();
                listView.setSelection(i);
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DEBUG", "onClick addButton !!");
                Film film = FilmCollection.getRandomFilm();
                filmAdapter.add(film);
                filmAdapter.notifyDataSetChanged();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DEBUG", "onClick updateButton !!");
                for (int i=0; i < filmAdapter.getCount(); i++) {
                    DownloadImage task = new DownloadImage(ListFilmAsyncTaskActivity.this, filmAdapter, filmAdapter.getItem(i));
                    // Execute task or execute on executor
                    // task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, URLImage);
                    task.execute(URLImage);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DEBUG", "clear films");
                filmAdapter.clear();
                filmAdapter.notifyDataSetChanged();
            }
        });
    }
}
