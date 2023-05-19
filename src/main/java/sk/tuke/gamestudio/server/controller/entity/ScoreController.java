package sk.tuke.gamestudio.server.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/score/{game}")
    public String getScore(@PathVariable String game, Model model) {
        List<Score> scores = scoreService.getTopScores(game);
        List<Score> topFiveScores = scores.stream().limit(5).collect(Collectors.toList());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Score score : topFiveScores) {
            Date date = score.getPlayedOn();
            score.setFormattedDate(score.getPlayedOn());
        }
        model.addAttribute("scores", topFiveScores);
        model.addAttribute("allScores", scores);
        return "score";
    }

}
