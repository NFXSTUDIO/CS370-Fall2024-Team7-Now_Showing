package com.nowshowing.UI.UIElements.MVC;

import com.nowshowing.UI.UIElements.UILabel;
import com.nowshowing.UI.UIElements.UIText;
import com.nowshowing.UI.UIElements.ViewController;
import com.nowshowing.wrappers.Media;

import javax.swing.*;

import java.util.ArrayList;

//this is the class for search results and watchlist
//the unique data here is all static since the user is never viewing more than 2 lists at once
public class ListLabel extends UILabel {
    static ArrayList<Media> mediaList;
    static int position;
    UIText displayTitleText;

    public ListLabel(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior){
        super(x, y, width, height, xBehavior, yBehavior);
        mediaList = null;
        position = 0;
    }

    public void setDependantObjects(UIText displayTitleText){
        this.displayTitleText = displayTitleText;
    }

    //use positive for next page, negative for previous page
    public void changePage(int amount) {
        position += amount;
        if(position < 0)
            position = 0;
        if(position >= mediaList.size())
            position = mediaList.size() - 1;
        ViewController.refreshActiveScene();
    }

    //for setting the search results
    public static void setDisplayList(ArrayList<Media> media){
        mediaList = media;
        ViewController.refreshActiveScene();
    }

    public static Media getDisplayedMedia(){
        if(mediaList == null)
            return null;
        return mediaList.get(position);
    }

    //don't refresh if no search results yet
    public void refresh(JComponent parent){
        //ensure position is in bounds
        if(position >= mediaList.size())
            position = mediaList.size() - 1;
        if(position < 0)
            position = 0;
        //show title and refresh if there is something to show
        if(!mediaList.isEmpty()){
            displayTitleText.setText(mediaList.get(position).getTitle());
            super.refresh(parent);
        }
    }
}
