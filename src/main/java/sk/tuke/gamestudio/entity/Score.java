package sk.tuke.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NamedQuery(name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC ")
@NamedQuery(name = "Score.resetScores",
        query = "DELETE FROM Score")
public class Score implements Serializable {
    @Id
    @GeneratedValue
    private int ident;

    private String player;
    private String game;
    private int points;
    private Date playedOn;
    private String formattedDate;

    public Score() {

    }

    public Score(String player, String game, int points, Date playedOn) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }


    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
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
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + formattedDate +
                '}';
    }

}
