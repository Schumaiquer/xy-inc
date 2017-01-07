package webservice.home.com.br.myapplication;
import android.graphics.Bitmap;

public class FilmeObj {

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Language;
    private String imdbID;
    private String imdbRating;
    private String Writer;


    private String Poster;
    private Bitmap imagem;

    public String getWriter() {return Writer;}

    public String getImdbRating(){return imdbRating;}

    public String getImdbID() {
        return imdbID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public String getPoster() {
        return Poster;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getActors() {
        return Actors;
    }

    public String getDirector() {
        return Director;
    }

    public String getGenre() {
        return Genre;
    }

    public String getLanguage() {
        return Language;
    }

    public String getPlot() {
        return Plot;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }
}
