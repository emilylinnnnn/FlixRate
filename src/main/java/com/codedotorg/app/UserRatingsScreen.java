package com.codedotorg.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codedotorg.Movie;
import com.codedotorg.Rating;
import com.codedotorg.User;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserRatingsScreen extends AppScreen {

    /**
     * This class represents the user ratings screen of the application.
     * It extends the Screen class and takes in a Stage object, width and height as parameters.
     * The constructor initializes the UserRatingsScreen object with the given parameters.
     *
     * @param window The Stage object representing the window of the application.
     * @param width The width of the user ratings screen.
     * @param height The height of the user ratings screen.
     */
    public UserRatingsScreen(Stage window, int width, int height) {
        super(window, width, height);
    }

    /**
     * Displays the user ratings screen by creating a VBox layout and setting it as the scene.
     */
    public void showScene() {
        VBox userRatingsLayout = createUserRatingsScreen();
        setAndShowScene(userRatingsLayout);
    }

    /**
     * Creates a VBox layout for the user ratings screen.
     * 
     * @return the VBox layout for the user ratings screen
     */
    public VBox createUserRatingsScreen() {
        VBox tempLayout = new VBox(10);
        tempLayout.setPadding(new Insets(10, 10, 10, 10));

        List<Node> componentsList = createComponents();

        tempLayout.getChildren().addAll(componentsList);

        return tempLayout;
    }

    /**
     * Creates and returns a list of UI components for the user ratings screen.
     * The list includes a user label, a list view, and a back button.
     *
     * @return a list of UI components for the user ratings screen
     */
        public List<Node> createComponents() {
            User currentUser = MovieApp.getCurrentUser();
            String userName = currentUser != null ? currentUser.getName() : "Unknown User";
            Label userLabel = new Label("Ratings for: " + userName);
            ListView<String> listView = createListView();
            Button backButton = getBackButton();
            return Arrays.asList(userLabel, listView, backButton);
        }

    /**
     * Creates a ListView of user ratings.
     * 
     * @return a ListView of Rating objects.
     */
        public ListView<String> createListView() {
            ListView<String> listView = new ListView<>();
            User currentUser = MovieApp.getCurrentUser();
            if (currentUser != null) {
                ArrayList<Rating> ratings = currentUser.getRatings();
                for (Rating rating : ratings) {
                    Movie movie = rating.getMovie();
                    String movieTitle = movie != null ? movie.getTitle() : "Unknown Movie";
                    int score = rating.getScore();
                    listView.getItems().add(movieTitle + ": " + score);
                }
            }
            return listView;
        }

}
