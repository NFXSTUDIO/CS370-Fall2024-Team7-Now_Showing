package com.nowshowing.UI.UIElements;

import java.awt.*;
import com.nowshowing.user.*;
import com.nowshowing.wrappers.Movie;

import java.util.ArrayList;
import java.util.List;

import com.nowshowing.ServerController;

public class ViewController {
    static ServerController serverController;
    static User currentUser;
    //the frame used to display everything. there will only ever be one
    static NowShowingFrame frame;
    //the various scenes of the program
    static Scene[] scenes;
    static Scene landingScene;
    static Scene loginScene;
    static Scene mainMenuScene;
    static Scene searchScene;
    static Scene watchlistScene;
    static Scene recommendationsScene;
    static Scene mediaInfoScene;
    //constants to refer to each scene
    public static final int LANDING_SCENE = 0;
    public static final int LOGIN_SCENE = 1;
    public static final int MAIN_MENU_SCENE = 2;
    public static final int SEARCH_SCENE = 3;
    public static final int WATCHLIST_SCENE = 4;
    public static final int RECOMMENDATIONS_SCENE = 5;
    public static final int MEDIA_INFO_SCENE = 6;

    //shorthand to make later code more readable when setting positioning methods
    final UIElement.PositioningMethod MIN = UIElement.PositioningMethod.MIN;
    final UIElement.PositioningMethod CENTER = UIElement.PositioningMethod.CENTER;
    final UIElement.PositioningMethod MAX = UIElement.PositioningMethod.MAX;
    final UIElement.PositioningMethod STRETCH = UIElement.PositioningMethod.STRETCH;
    final UIElement.PositioningMethod PERCENT = UIElement.PositioningMethod.PERCENT_FILL;

    public ViewController(){
        createScenes();
        serverController = new ServerController(this);
        // Possibly: use default User constructor that can identify itself as not logged in (ie id = -1)
    }

    public static void loadScene(int sceneID){
        //make sure frame isn't null
        verifyFrame();
        //add scene to frame
        frame.setScene(scenes[sceneID]);
        scenes[sceneID].refresh();
    }

    static void verifyFrame(){
        //create frame if it doesn't exist yet
        if(frame == null)
            frame = new NowShowingFrame(1920/2,1080/2);
    }

    public static void attemptLogIn(String username, String password){
        //insert method call to the services controller
        //TODO: implement way to check for success
        currentUser = serverController.loginRequest(username, password);
        

        //temporary function for debugging purposes:
        displayLogInResult(true);
    }

    public static void attemptLogout(){
        //insert method call to the services controller

        //temporary function for debugging purposes:
        displayLogOutResult();
    }

    public static void displayLogInResult(boolean success){
        if(success)
            loadScene(MAIN_MENU_SCENE);
        else{
            //load the register account scene; not yet implemented
        }
    }

    public static void displayLogOutResult(){
        loadScene(LANDING_SCENE);
    }

    public void getRecommendation(){
        List<String> titles = new ArrayList<String>();
        // TODO: load movie titles into list

        Movie result = serverController.recommendationRequest(titles);
        // TODO: display results
    }

    public void removeFromWatchlist(String title){
        serverController.removeFromWL(title, currentUser.getid()); // Replace with any way to identify user
    }

    public void addToWatchlist(String title){
        serverController.addToWL(title, currentUser.getId()); // Replace with any way to identify user
    }

    public void getSearchResults(String input){
        List<Movie> results = serverController.searchRequest(input);
        //TODO: display results
    }



    //this method creates each scene
    void createScenes(){
        //landing page scene
        landingScene = new Scene();
        //header
        UIElement landingHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        landingScene.addElement(landingHeader);
        UIElement landingHeaderText = new UIText("Welcome", new Color(0xffffff));
        landingHeader.addElement(landingHeaderText);
        //log in button
        UIElement logInButton = new LoginButton(0,0,200,100, CENTER, CENTER, new Color(0x811A0A));
        landingScene.addElement(logInButton);
        UIElement logInButtonText = new UIText("Log In", new Color(0xffffff));
        logInButton.addElement(logInButtonText);

        //log in scene
        loginScene = new Scene();
        //header
        UIElement loginHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        loginScene.addElement(loginHeader);
        UIElement loginHeaderText = new UIText("Log In", new Color(0xffffff));
        loginHeader.addElement(loginHeaderText);
        //username field
        UIElement usernameAskText = new UIText(100,-100,100,50, STRETCH, CENTER, new Color(0x000000), "Username:");
        loginScene.addElement(usernameAskText);
        UIElement usernameInputBackdrop = new UILabel(100,-50,100,50, STRETCH, CENTER, new Color(0xffffff));
        loginScene.addElement(usernameInputBackdrop);
        UITextInput usernameInputText = new UITextInput();
        usernameInputBackdrop.addElement(usernameInputText);
        //password field
        UIElement passwordAskText = new UIText(100,0,100,50, STRETCH, CENTER, new Color(0x000000), "Password:");
        loginScene.addElement(passwordAskText);
        UIElement passwordInputBackdrop = new UILabel(100,50,100,50, STRETCH, CENTER, new Color(0xffffff));
        loginScene.addElement(passwordInputBackdrop);
        UITextInput passwordInputText = new UITextInput();
        passwordInputBackdrop.addElement(passwordInputText);
        //log in button
        ConfirmLoginButton finishLogInButton = new ConfirmLoginButton(0,150,200,100, CENTER, CENTER, new Color(0x811A0A));
        finishLogInButton.setInputObjects(usernameInputText, passwordInputText);
        loginScene.addElement(finishLogInButton);
        UIElement finishLogInButtonText = new UIText("Log In", new Color(0xffffff));
        finishLogInButton.addElement(finishLogInButtonText);

        //main menu scene
        mainMenuScene = new Scene();
        //header
        UIElement mainMenuHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        mainMenuScene.addElement(mainMenuHeader);
        UIElement mainMenuHeaderText = new UIText("Main Menu", new Color(0xffffff));
        mainMenuHeader.addElement(mainMenuHeaderText);
        //search media button
        UIElement searchMediaButton = new UIButton(-125,-50,200,150, CENTER, CENTER, new Color(0x811A0A));
        mainMenuScene.addElement(searchMediaButton);
        UIElement searchMediaButtonText = new UIText("Search Media", new Color(0xffffff));
        searchMediaButton.addElement(searchMediaButtonText);
        //view watchlist button
        UIElement viewWatchlistButton = new UIButton(125,-50,200,150, CENTER, CENTER, new Color(0x811A0A));
        mainMenuScene.addElement(viewWatchlistButton);
        UIElement viewWatchlistButtonText = new UIText("View Watchlist", new Color(0xffffff));
        viewWatchlistButton.addElement(viewWatchlistButtonText);
        //get recommendations button
        UIElement recommendationsButton = new UIButton(-125,150,200,150, CENTER, CENTER, new Color(0x811A0A));
        mainMenuScene.addElement(recommendationsButton);
        UIElement recommendationsButtonText = new UIText("Get Recommendation", new Color(0xffffff));
        recommendationsButton.addElement(recommendationsButtonText);
        //log out button
        UIElement logoutButton = new LogoutButton(125,150,200,150, CENTER, CENTER, new Color(0x811A0A));
        mainMenuScene.addElement(logoutButton);
        UIElement logoutButtonText = new UIText("Log out", new Color(0xffffff));
        logoutButton.addElement(logoutButtonText);

        //search scene
        searchScene = new Scene();
        //header
        UIElement searchHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        searchScene.addElement(searchHeader);
        UIElement searchHeaderText = new UIText("Search", new Color(0xffffff));
        searchHeader.addElement(searchHeaderText);
        //search input field
        UIElement searchAskText = new UIText(50,100,200,50, STRETCH, MIN, new Color(0x000000), "Search Query:");
        searchScene.addElement(searchAskText);
        UIElement searchInputBackdrop = new UILabel(20,150,200,50, STRETCH, MIN, new Color(0xffffff));
        searchScene.addElement(searchInputBackdrop);
        UIElement searchInputText = new UITextInput();
        searchInputBackdrop.addElement(searchInputText);
        //search button
        UIElement searchButton = new UIButton(-175,125,150,75, MAX, MIN, new Color(0x811A0A));
        searchScene.addElement(searchButton);
        UIElement searchButtonText = new UIText("Search", new Color(0xffffff));
        searchButton.addElement(searchButtonText);

        //watchlist scene (save for later)
        watchlistScene = new Scene();
        //header
        UIElement watchlistHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        watchlistScene.addElement(watchlistHeader);
        UIElement watchlistHeaderText = new UIText("Your Watchlist", new Color(0xffffff));
        watchlistHeader.addElement(watchlistHeaderText);

        //recommendations scene (save for later)
        recommendationsScene = new Scene();
        //header
        UIElement recommendationsHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        recommendationsScene.addElement(recommendationsHeader);
        UIElement recommendationsHeaderText = new UIText("Recommendations", new Color(0xffffff));
        recommendationsHeader.addElement(recommendationsHeaderText);

        //media info scene
        mediaInfoScene = new Scene();
        //header
        UIElement mediaInfoHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        mediaInfoScene.addElement(mediaInfoHeader);
        UIElement mediaInfoHeaderText = new UIText("Media Details", new Color(0xffffff));
        mediaInfoHeader.addElement(mediaInfoHeaderText);

        //add to scenes array
        scenes = new Scene[7];
        scenes[0] = landingScene;
        scenes[1] = loginScene;
        scenes[2] = mainMenuScene;
        scenes[3] = searchScene;
        scenes[4] = watchlistScene;
        scenes[5] = recommendationsScene;
        scenes[6] = mediaInfoScene;
    }
}
