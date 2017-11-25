package fr.parisnanterre.pmoo.androidm2td1.adapter;

import java.util.Random;

import fr.parisnanterre.pmoo.androidm2td1.model.Film;
import fr.parisnanterre.pmoo.androidm2td1.util.Time;


public class FilmCollection {
    private static Random random = new Random();
    private static Film[] films = {
            new Film("Harry Potter and the Prisoner of Azkaban", "Warner Bros", Time.getRandomTimestamp(), null),
            new Film("The Ring", "DreamWorks Pictures", Time.getRandomTimestamp(), null),
            new Film("Wonderstruck", "Killer Films", Time.getRandomTimestamp(), null),
            new Film("Zootopia", "Walt Disney Pictures", Time.getRandomTimestamp(), null),
            new Film("007 Spectre", "Columbia Pictures", Time.getRandomTimestamp(), null),
            new Film("The Amazing Spiderman", "Marvel", Time.getRandomTimestamp(), null),
            new Film("Les Garçons et Guillaume, à table !", "Gaumont", Time.getRandomTimestamp(), null),
            new Film("Les gardiens de la galaxie", "Walt Disney", Time.getRandomTimestamp(), null),
            new Film("Interstellar", "Warner Bros", Time.getRandomTimestamp(), null),
            new Film("Pulp Fiction", "A Band Apart", Time.getRandomTimestamp(), null),
            new Film("Pirates des caraibes", "Walt Disney", Time.getRandomTimestamp(), null),
            new Film("Star Wars - the last jedi", "Walt Disney", Time.getRandomTimestamp(), null),
            new Film("Batman Begins", "DC Comics", Time.getRandomTimestamp(), null),
            new Film("La légende de tarzan", " Walt Disney", Time.getRandomTimestamp(), null),
            new Film("Panique à bord", " Andrew L. Stone", Time.getRandomTimestamp(), null)

    };
    private static int size = 15;

    public static Film getRandomFilm() {
        int rand = random.nextInt(size);
        return new Film(films[rand]);
    }
}
