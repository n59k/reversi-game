package sk.tuke.gamestudio.server.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/score/{game}")
    public String getScore(@PathVariable String game, Model model) {
        List<Score> scores = scoreService.getTopScores(game);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Score score : scores) {
            Date date = score.getPlayedOn();
            score.setFormattedDate(score.getPlayedOn());
        }
        model.addAttribute("scores", scores);
        return "score";
    }
}
