package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

//temporary for testing
//later will be made abstract and split into button and panel classes
public abstract class UIElement {
    float x;
    float y;
    float width;
    float height;
    PositioningMethod xBehavior;
    PositioningMethod yBehavior;
    ArrayList<UIElement> children;
    Color color;

    //created during refresh. also destroyed before each refresh
    JComponent component;

    public UIElement(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height= height;
        this.xBehavior = xBehavior;
        this.yBehavior = yBehavior;
        this.color = color;
        children = new ArrayList<UIElement>();
    }

    public UIElement(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior){
        this(x,y,width,height,xBehavior, yBehavior, new Color(0x7A424F));
    }

    public UIElement(float x, float y, float width, float height){
        this(x,y,width,height,PositioningMethod.CENTER,PositioningMethod.CENTER);
    }

    //outer bounds = width and height of the parent object
    //precondition: this object, all its child objects, and the scene it's in are properly sized
    //              i.e. this should be done as part of refresh, after the object is sized
    void addToParent(JComponent parent, Component component){
        parent.add(component);
    }

    public void addElement(UIElement element){
        children.add(element);
    }

    public void refresh(JComponent parent){
        beforeRefresh();
        //get positioning values
        float[] xVals = getAxisValues(x, width, xBehavior, parent.getWidth());
        float[] yVals = getAxisValues(y, height, yBehavior, parent.getHeight());
        //System.out.println(xVals[0] + " " + yVals[0] + " " + xVals[1] + " " + yVals[1]);

        //create JCcomponent. will be different depending on what overridden createComponent() returns
        component = createComponent();

        //set position and width/height
        component.setBounds((int)xVals[0], (int)yVals[0], (int)xVals[1], (int)yVals[1]);

        //set positioning so other objects are added from top left
        component.setLayout(null);

        //add to panel
        addToParent(parent, component);

        //make visible
        setColor(component);
        component.setVisible(true);
        component.revalidate();
        component.repaint();

        //refresh all child elements
        for(UIElement e : children){
            e.refresh(component);
        }
    }

    void beforeRefresh(){
        //method is here to be overridden by subclasses that need it
    }

    //should be overridden by subclass depending on what swing component it represents
    //default: JLabel
    abstract JComponent createComponent();

    void setColor(JComponent component){
        component.setBackground(color);
        component.setOpaque(true);
    }

    //returns position and width values for a given positioning method
    //behavior = the positioning method to be used
    //boundsDelta = the length of the container for the axis being used
    float[] getAxisValues(float pos, float length, PositioningMethod behavior, float parentLength){
        return switch (behavior) {
            case MIN -> getAxisValuesMin(pos, length, parentLength);
            case CENTER -> getAxisValuesCenter(pos, length, parentLength);
            case MAX -> getAxisValuesMax(pos, length, parentLength);
            case STRETCH -> getAxisValuesStretch(pos, length, parentLength);
            case PERCENT_FILL -> getAxisValuesPercentFill(pos, length, parentLength);
        };
    }

    //returns axis values using MIN method
    float[] getAxisValuesMin(float pos, float length, float parentLength){
        float[] vals = new float[2];
        //position
        vals[0] = pos;
        //width
        vals[1] = length;
        //return
        return vals;
    }
    //returns axis values using CENTER method
    float[] getAxisValuesCenter(float pos, float length, float parentLength){
        float[] vals = new float[2];
        //position
        vals[0] = pos - length / 2 + parentLength / 2;
        //width
        vals[1] = length;
        //return
        return vals;
    }
    //returns axis values using MAX method
    float[] getAxisValuesMax(float pos, float length, float parentLength){
        float[] vals = new float[2];
        //position
        vals[0] = pos - length + parentLength;
        //width
        vals[1] = length;
        //return
        return vals;
    }
    //returns axis values using STRETCH method
    float[] getAxisValuesStretch(float pos, float length, float parentLength){
        float[] vals = new float[2];
        //position
        vals[0] = pos;
        //width
        vals[1] = parentLength - pos - length;
        //return
        return vals;
    }
    //returns axis values using PERCENT_FILL method
    float[] getAxisValuesPercentFill(float pos, float length, float parentLength){
        float[] vals = new float[2];
        //position
        vals[0] = parentLength * pos;
        //width
        vals[1] = parentLength * (1 - pos - length);
        //return
        return vals;
    }

    public enum PositioningMethod{
        //top-left if both coords min
        MIN,
        //centered if both coords center
        CENTER,
        //bottom-right if both coords max
        MAX,
        //stretches across screen.
        //x is used as distance from left, width as distance from right
        //y is used as distance from top, height as distance from left
        STRETCH,
        //stretches across screen.
        //all values should be a float 0-1
        //x is percent distance from left, width is percent distance from right
        //y is percent distance from top, height is percent distance from bottom
        PERCENT_FILL
    }
}
