package com.nowshowing.UI.UIElements;

import com.nowshowing.wrappers.Media;
import com.nowshowing.wrappers.Movie;
import com.nowshowing.wrappers.TVShow;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewController {
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

    //scene to be returned to after leaving media info
    public static Scene returnScene = null;

    //shorthand to make later code more readable when setting positioning methods
    final UIElement.PositioningMethod MIN = UIElement.PositioningMethod.MIN;
    final UIElement.PositioningMethod CENTER = UIElement.PositioningMethod.CENTER;
    final UIElement.PositioningMethod MAX = UIElement.PositioningMethod.MAX;
    final UIElement.PositioningMethod STRETCH = UIElement.PositioningMethod.STRETCH;
    final UIElement.PositioningMethod PERCENT = UIElement.PositioningMethod.PERCENT_FILL;

    public ViewController(){
        createScenes();
    }

    public static void loadScene(int sceneID){
        //make sure frame isn't null
        verifyFrame();
        //add scene to frame
        frame.setScene(scenes[sceneID]);
        scenes[sceneID].refresh();
    }

    //returns true if successful, false if unsuccessful
    public static boolean returnToPreviousScene(){
        if(returnScene == null)
            return false;
        frame.setScene(returnScene);
        returnScene.refresh();
        //once set, we can set the return scene to null to reset it
        returnScene = null;
        return true;
    }

    public static void refreshActiveScene(){
        frame.getScene().refresh();
    }

    static void verifyFrame(){
        //create frame if it doesn't exist yet
        if(frame == null)
            frame = new NowShowingFrame(1920*2/3,1080*2/3);
    }

    public static void attemptLogIn(String username, String password){
        //insert method call to the services controller

        //temporary function for debugging purposes:
        displayLogInResult(true);
    }

    public static void attemptLogout(){
        //insert method call to the services controller

        //temporary line for debugging purposes:
        displayLogOutResult();
    }

    public static void attemptAddToWatchlist(int mediaID){
        //inset method call to the services controller
    }

    //note to gavin: this needs to actually be called somewhere still
    public void attemptGetRecommendation(){
        //inset method call to the services controller
    }

    public static void attemptViewMediaInfo(int mediaID){
        //inset method call to the services controller

        //temporary line for debugging purposes:

        Movie m = new Movie();
        m.setTitle("media 0 (movie)");
        m.setId(0);
        m.setDirector("director");
        m.setRuntime(120);
        m.setCast(new ArrayList<String>());
        m.setOverview("this isn't displayed as of now");
        displayMediaInfo(m);
    }

    public static void search(String query) {
        //insert method call to the services controller

        System.out.println("search sent: " + query);

        //temporary code for debugging purposes
        ArrayList<Media> arr = new ArrayList<Media>();
        arr.add(new Movie());
        arr.add(new TVShow());
        arr.add(new Movie());
        ((Movie)arr.get(0)).setTitle("media 0 (movie)");
        ((TVShow)arr.get(1)).setName("media 1 (tv show)");
        ((Movie)arr.get(2)).setTitle("media 2 (movie)");
        arr.get(0).setId(0);
        arr.get(1).setId(1);
        arr.get(2).setId(2);
        displaySearchResults(arr);
    }

    public static void displayLogInResult(boolean success){
        if(success)
            loadScene(MAIN_MENU_SCENE);
        else{
            //load the register account scene; not yet implemented
        }
    }

    public static void displayRegisterResult(boolean success){
        if(success){
            loadScene(MAIN_MENU_SCENE);
        }
        else{
            //clear register page to try again; not yet implemented
        }
    }

    public static void displaySearchResults(ArrayList<Media> searchResults){
        ListLabel.setSearchResults(searchResults);
    }

    public static void displayMediaInfo(Media media){
        returnScene = frame.getScene();
        MediaInfoText.setDisplayedMedia(media);
        loadScene(MEDIA_INFO_SCENE);

    }

    public static void displayLogOutResult(){
        loadScene(LANDING_SCENE);
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
        UIElement logInButton = new SceneSwitchButton(0,0,200,100, CENTER, CENTER, new Color(0x811A0A),LOGIN_SCENE);
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
        LoginButton finishLogInButton = new LoginButton(0,150,200,100, CENTER, CENTER, new Color(0x811A0A));
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
        //backdrop for options
        UIElement mainMenuBackdrop = new UILabel(10, 110, 10, 10, STRETCH, STRETCH, new Color(0xdadada));
        mainMenuScene.addElement(mainMenuBackdrop);
        //search media button
        UIElement searchMediaButton = new SceneSwitchButton(0.05f,0.05f,0.525f,0.525f, PERCENT, PERCENT, new Color(0x811A0A),SEARCH_SCENE);
        mainMenuBackdrop.addElement(searchMediaButton);
        UIElement searchMediaButtonText = new UIText("Search Media", new Color(0xffffff));
        searchMediaButton.addElement(searchMediaButtonText);
        //view watchlist button
        UIElement viewWatchlistButton = new SceneSwitchButton(0.525f,0.05f,0.05f,0.525f, PERCENT, PERCENT, new Color(0x811A0A), WATCHLIST_SCENE);
        mainMenuBackdrop.addElement(viewWatchlistButton);
        UIElement viewWatchlistButtonText = new UIText("View Watchlist", new Color(0xffffff));
        viewWatchlistButton.addElement(viewWatchlistButtonText);
        //get recommendations button
        UIElement recommendationsButton = new SceneSwitchButton(0.05f,0.525f,0.525f,0.05f, PERCENT, PERCENT, new Color(0x811A0A), RECOMMENDATIONS_SCENE);
        mainMenuBackdrop.addElement(recommendationsButton);
        UIElement recommendationsButtonText = new UIText("Get Recommendation", new Color(0xffffff));
        recommendationsButton.addElement(recommendationsButtonText);
        //log out button
        UIElement logoutButton = new LogoutButton(0.525f,0.525f,0.05f, 0.05f, PERCENT, PERCENT, new Color(0x811A0A));
        mainMenuBackdrop.addElement(logoutButton);
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
        UITextInput searchInputText = new UITextInput();
        searchInputBackdrop.addElement(searchInputText);
        //search button
        SearchButton searchButton = new SearchButton(-25,125,150,75, MAX, MIN, new Color(0x811A0A));
        searchButton.setInputObjects(searchInputText);
        searchScene.addElement(searchButton);
        UIElement searchButtonText = new UIText("Search", new Color(0xffffff));
        searchButton.addElement(searchButtonText);
        //search result
        ListLabel searchResult = new ListLabel(0.1f, 250, 0.1f, 100, PERCENT, STRETCH);
        searchScene.addElement(searchResult);
        UIText searchMediaTitle = new UIText(10,10,10,50, STRETCH, MIN, new Color(0xffffff), "Media Title");
        searchResult.addElement(searchMediaTitle);
        searchResult.setDependantObjects(searchMediaTitle);
            //add to watchlist button
        AddToWatchlistButton addToWatchlistFromSearchButton = new AddToWatchlistButton(0.05f, 75, 0.525f, 30, PERCENT, STRETCH, new Color(0x811A0A));
        addToWatchlistFromSearchButton.setListLabel(searchResult);
        searchResult.addElement(addToWatchlistFromSearchButton);
        UIText addToWatchlistFromSearchText = new UIText("Add to Watchlist", new Color(0xffffff));
        addToWatchlistFromSearchButton.addElement(addToWatchlistFromSearchText);
            //view media info button
        UIButton viewMediaInfoFromSearchButton = new ViewMediaInfoButton(0.525f, 75, 0.05f, 30, PERCENT, STRETCH, new Color(0x811A0A));
        searchResult.addElement(viewMediaInfoFromSearchButton);
        UIText viewMediaInfoFromSearchText = new UIText("View Media Info", new Color(0xffffff));
        viewMediaInfoFromSearchButton.addElement(viewMediaInfoFromSearchText);
        //previous page button
        PageChangeButton previousSearchButton = new PageChangeButton(-180, -10, 350, 75, CENTER, MAX, new Color(0x811A0A),-1);
        previousSearchButton.setListLabel(searchResult);
        searchScene.addElement(previousSearchButton);
        UIElement previousSearchButtonText = new UIText("Previous Result", new Color(0xffffff));
        previousSearchButton.addElement(previousSearchButtonText);
        //next page button
        PageChangeButton nextSearchButton = new PageChangeButton(180, -10, 350, 75, CENTER, MAX, new Color(0x811A0A),1);
        nextSearchButton.setListLabel(searchResult);
        searchScene.addElement(nextSearchButton);
        UIElement nextSearchButtonText = new UIText("Next Result", new Color(0xffffff));
        nextSearchButton.addElement(nextSearchButtonText);
        //exit button
        UIElement exitSearchButton = new SceneSwitchButton(10, -10, 100, 50, MIN, MAX, new Color(0x811A0A), MAIN_MENU_SCENE);
        searchScene.addElement(exitSearchButton);
        UIElement exitSearchButtonText = new UIText("Exit", new Color(0xffffff));
        exitSearchButton.addElement(exitSearchButtonText);

        //watchlist scene (save for later)
        watchlistScene = new Scene();
        //header
        UIElement watchlistHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        watchlistScene.addElement(watchlistHeader);
        UIElement watchlistHeaderText = new UIText("Your Watchlist", new Color(0xffffff));
        watchlistHeader.addElement(watchlistHeaderText);
        //exit button
        UIElement exitWatchlistButton = new SceneSwitchButton(10, -10, 100, 50, MIN, MAX, new Color(0x811A0A), MAIN_MENU_SCENE);
        watchlistScene.addElement(exitWatchlistButton);
        UIElement exitWatchlistButtonText = new UIText("Exit", new Color(0xffffff));
        exitWatchlistButton.addElement(exitWatchlistButtonText);

        //recommendations scene (save for later)
        recommendationsScene = new Scene();
        //header
        UIElement recommendationsHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        recommendationsScene.addElement(recommendationsHeader);
        UIElement recommendationsHeaderText = new UIText("Recommendations", new Color(0xffffff));
        recommendationsHeader.addElement(recommendationsHeaderText);
        //exit button
        UIElement exitRecommendationsButton = new SceneSwitchButton(10, -10, 100, 50, MIN, MAX, new Color(0x811A0A), MAIN_MENU_SCENE);
        recommendationsScene.addElement(exitRecommendationsButton);
        UIElement exitRecommendationsButtonText = new UIText("Exit", new Color(0xffffff));
        exitRecommendationsButton.addElement(exitRecommendationsButtonText);

        //media info scene
        mediaInfoScene = new Scene();
        //header
        UIElement mediaInfoHeader = new UILabel(0,0,0,100, STRETCH, MIN);
        mediaInfoScene.addElement(mediaInfoHeader);
        UIElement mediaInfoHeaderText = new UIText("Media Details", new Color(0xffffff));
        mediaInfoHeader.addElement(mediaInfoHeaderText);
        //info text
        MediaInfoText mediaInfoText = new MediaInfoText(200, 100, 100, 100, STRETCH, STRETCH, new Color(0x000000));
        mediaInfoScene.addElement(mediaInfoText);
        //exit button
        UIElement exitInfoButton = new SceneSwitchButton(10, -10, 100, 50, MIN, MAX, new Color(0x811A0A), MAIN_MENU_SCENE);
        mediaInfoScene.addElement(exitInfoButton);
        UIElement exitInfoButtonText = new UIText("Exit", new Color(0xffffff));
        exitInfoButton.addElement(exitInfoButtonText);

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
