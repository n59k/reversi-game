package sk.tuke.gamestudio.service;

import org.springframework.transaction.annotation.Transactional;
import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.persist(rating);
    }

    @Override
    public List<Rating> getRatings(String game) throws RatingException {
        return entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game)
                .getResultList();
    }



    @Override
    public int getRating(String game) throws RatingException {
        return ((Rating) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game).getSingleResult()).getRating();
    }

    @Override
    public void updateRating(Rating rating) {
        entityManager.createQuery("UPDATE Rating r SET r.rating = :newRating, r.ratedOn = :newDate WHERE r.game = :game AND r.player = :player")
                .setParameter("newRating", rating.getRating())
                .setParameter("newDate", rating.getRatedOn())
                .setParameter("game", rating.getGame())
                .setParameter("player", rating.getPlayer())
                .executeUpdate();
    }

    @Override
    public int getRatingAll(String game, String player) {
        TypedQuery<Rating> query = entityManager.createQuery(
                "SELECT r FROM Rating r WHERE r.game = :game AND r.player = :player", Rating.class);
        query.setParameter("game", game);
        query.setParameter("player", player);
        List<Rating> ratings = query.getResultList();
        if (ratings.isEmpty()) {
            return -1;
        } else {
            return ratings.get(0).getRating();
        }
    }
    public double getAverageRating(String game) {
        try {
            return (double) entityManager.createNamedQuery("Rating.getAverageRating")
                    .setParameter("game", game)
                    .getSingleResult();
        } catch (NoResultException e) {
            return -1;
        }
    }

}
