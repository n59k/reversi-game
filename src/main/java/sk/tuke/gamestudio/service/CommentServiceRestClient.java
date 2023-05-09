package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Arrays;
import java.util.List;

public class CommentServiceRestClient implements CommentService {
    private final String URL = "http://localhost:8080/api/comment";

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addComment(Comment comment) throws CommentException {
        restTemplate.postForEntity(URL, comment, Comment.class);


    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return Arrays.asList(restTemplate.getForEntity(URL + "/" + game, Comment[].class).getBody());
    }

    @Override
    public void reset() throws CommentException {
        restTemplate.exchange(URL, HttpMethod.DELETE, null, Void.class);
    }
}
