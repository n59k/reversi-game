package sk.tuke.gamestudio.server.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/comments/{game}")
    public String addComment(@PathVariable String game,
                             @RequestParam("playerName") String player,
                             @RequestParam String comment) {
        Comment newComment = new Comment(player, game, comment, new Date());
        commentService.addComment(newComment);
        return "redirect:/comments/" + game;
    }


    @GetMapping("/comments/{game}")
    public String getComments(@PathVariable String game, Model model) {
        List<Comment> comments = commentService.getComments(game);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Comment comment : comments) {
            Date date = comment.getCommentedOn();
            comment.setFormattedDate(comment.getCommentedOn());
        }
        model.addAttribute("comments", comments);
        return "comments";
    }

}
