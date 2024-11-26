package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene {
    ArrayList<UIElement> elements;
    JPanel panel;

    NowShowingFrame frame = null;

    public Scene(ArrayList<UIElement> elements){
        this.elements = elements;
    }

    public Scene(){
        this(new ArrayList<UIElement>());
    }

    public void addElement(UIElement element){
        elements.add(element);
    }

    public void addElement(int pos, UIElement element){
        elements.add(pos, element);
    }

    public void removeElement(int pos){
        elements.remove(pos);
    }

    public void addToFrame(NowShowingFrame frame){
        this.frame = frame;
    }

    //for now empties the scene and makes new objects
    //could be optimized later
    public void refresh(){
        //print a message and return if there's no frame
        if(frame == null){
            System.out.println("UIElements.Scene has not been added to a frame");
            return;
        }
        //remove all the elements
        frame.clear();

        //recreate panel
        panel = new JPanel();

        //set the size of the panel to be the same as the frame
        panel.setSize(frame.getSize());

        //set positioning so other objects are added from top left
        panel.setLayout(null);

        //add to panel and position
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        //System.out.println(frame.getWidth() + " " + frame.getHeight());
        frame.add(panel);

        //refresh panel
        panel.setBackground(new Color(0xDADADA));
        panel.setVisible(true);
        panel.revalidate();
        panel.repaint();

        //refresh all the elements
        for(UIElement element : elements){
            //System.out.println("bees 2");
            element.refresh(panel);
        }

        //refresh frame
        frame.refresh();
    }

}
