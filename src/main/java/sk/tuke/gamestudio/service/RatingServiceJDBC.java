//package sk.tuke.gamestudio.service;
//
//import sk.tuke.gamestudio.entity.Rating;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class RatingServiceJDBC implements RatingService {
//
//    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
//    public static final String USER = "postgres";
//    public static final String PASSWORD = "1";
//    public static final String SELECT = "SELECT AVG(rating) FROM rating WHERE game = ?";
//    public static final String SELECT_PLAYER = "SELECT rating FROM rating WHERE game = ? AND player = ?";
//    public static final String DELETE = "DELETE FROM rating";
//    public static final String INSERT = "INSERT INTO rating (player, game, rating, ratedon) VALUES (?, ?, ?, ?)";
//
//    @Override
//    public void setRating(Rating rating) throws RatingException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(INSERT)) {
//            statement.setString(1, rating.getPlayer());
//            statement.setString(2, rating.getGame());
//            statement.setInt(3, rating.getRating());
//            statement.setTimestamp(4, new java.sql.Timestamp(rating.getRatedOn().getTime()));
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RatingException("Problem inserting rating", e);
//        }
//    }
//
//    @Override
//    public int getAverageRating(String game) throws RatingException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(SELECT)) {
//            statement.setString(1, game);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            } else {
//                throw new RatingException("No rating found for game: " + game);
//            }
//        } catch (SQLException e) {
//            throw new RatingException("Failed to get average rating", e);
//        }
//    }
//
//    @Override
//    public int getRating(String game) throws RatingException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(SELECT_PLAYER)) {
//            statement.setString(1, game);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            } else {
//                throw new RatingException("No rating found for: ");
//            }
//        } catch (SQLException e) {
//            throw new RatingException("Failed to get rating", e);
//        }
//    }
//
//    @Override
//    public void reset() throws RatingException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(DELETE)) {
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RatingException("Failed to reset ratings", e);
//        }
//    }
//}
