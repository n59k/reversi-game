package sk.tuke.gamestudio.server.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @PostMapping("/rating/{game}")
    public String addRating(@PathVariable String game,
                            @RequestParam("playerName") String player,
                            @RequestParam("rating") int rating) {
        if (rating < 1) {
            rating = 1;
        } else if (rating > 5) {
            rating = 5;
        }

        Rating newRating = new Rating(player, game, rating, new Date());
        int existingRating = ratingService.getRatingAll(game, player);

        if (existingRating == -1) {
            ratingService.setRating(newRating);
        } else {
            ratingService.updateRating(newRating);
        }

        return "redirect:/rating/" + game;
    }


    @GetMapping("/rating/{game}")
    public String getRatings(@PathVariable String game, Model model) {
        List<Rating> ratings = ratingService.getRatings(game);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Rating rating : ratings) {
            Date date = rating.getRatedOn();
            rating.setFormattedDate(rating.getRatedOn());
        }
        double averageRating = ratingService.getAverageRating(game);
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedAverageRating = df.format(averageRating);
        model.addAttribute("ratings", ratings);
        model.addAttribute("averageRating", formattedAverageRating);
        return "rating";
    }

}
