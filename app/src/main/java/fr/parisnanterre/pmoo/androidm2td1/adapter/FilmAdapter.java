package fr.parisnanterre.pmoo.androidm2td1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.parisnanterre.pmoo.androidm2td1.R;
import fr.parisnanterre.pmoo.androidm2td1.model.Film;


public class FilmAdapter extends ArrayAdapter<Film> {

    public FilmAdapter(@NonNull Context context, List<Film> films) {
        super(context, R.layout.film_layout, films);
    }

    private static class ViewHolder {
        TextView title;
        TextView production;
        TextView date;
        ImageView image;
        int position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Film film = getItem(position);

        // Best practice https://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.film_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title        = convertView.findViewById(R.id.film_layout_textview_title);
            viewHolder.date         = convertView.findViewById(R.id.film_layout_textview_date);
            viewHolder.production   = convertView.findViewById(R.id.film_layout_textview_production);
            viewHolder.image        = convertView.findViewById(R.id.film_layout_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.position = position;
        viewHolder.title.setText(film.title);
        viewHolder.date.setText(film.getPrettyDate());
        viewHolder.production.setText(film.production);
        if (film.image != null)
            viewHolder.image.setImageBitmap(film.image);
        else
            viewHolder.image.setImageResource(android.R.drawable.presence_busy);

        return convertView;
    }


}
