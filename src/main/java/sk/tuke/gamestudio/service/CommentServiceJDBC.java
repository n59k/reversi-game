package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService {
    public final String URL = "jdbc:postgresql://localhost/gamestudio";
    public final String USER = "postgres";
    public final String PASSWORD = "1";
    public final String SELECT = "SELECT player, game, comment, commentedon FROM comment WHERE game = ? ORDER BY player DESC";
    public final String DELETE = "DELETE FROM comment";
    public final String INSERT = "INSERT INTO comment (player, game, comment, commentedon) VALUES (?, ?, ?, ?)";


    @Override
    public void addComment(Comment comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, comment.getPlayer());
            statement.setString(2, comment.getGame());
            statement.setString(3, comment.getComment());
            statement.setTimestamp(4, new Timestamp(comment.getCommentedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("Error adding comment", e);
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT)
        ) {
            statement.setString(1, game);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String player = rs.getString("player");
                String comment = rs.getString("comment");
                Timestamp commentedOn = rs.getTimestamp("commentedOn");
                Comment c = new Comment(player, game, comment, commentedOn);
                comments.add(c);
            }
        } catch (SQLException e) {
            throw new CommentException("Error getting comments", e);
        }
        return comments;
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting score", e);
        }
    }

}
