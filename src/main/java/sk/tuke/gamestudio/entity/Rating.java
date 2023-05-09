package sk.tuke.gamestudio.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@NamedQuery(name = "Rating.getAverageRating", query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game = :game")
@NamedQuery(name = "Rating.getRating", query = "SELECT r FROM Rating r WHERE r.game = :game ORDER BY r.ratedOn DESC ")
@NamedQuery(name = "Rating.resetRatings", query = "DELETE FROM Rating")
@Entity
public class Rating implements Serializable {
    @Id
    @GeneratedValue
    private int identrating;

    private String player;
    private String game;
    private int rating;
    private Date ratedOn;
    private String formattedDate;

    public Rating() {
    }

    public Rating(String player, String game, int rating, Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public int getIdent() {
        return identrating;
    }

    public void setIdent(int ident) {
        this.identrating = ident;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setRating(int rating) {
        if (rating < 1) {
            this.rating = 1;
        } else if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = rating;
        }
    }


    public int getRating() {
        return rating;
    }

    public Date getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(Date ratedOn) {
        this.ratedOn = ratedOn;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy HH:mm");
        this.formattedDate = formatter.format(date);
    }
    @Override
    public String toString() {
        return "Rating{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + formattedDate +
                '}';
    }


}
