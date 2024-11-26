package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;

public class UITextInput extends UIElement{

    private String text = "";

    public UITextInput(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public UITextInput(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior){
        this(x, y, width, height, xBehavior, yBehavior, new Color(0x0000));
    }

    public UITextInput(float x, float y, float width, float height){
        this(x, y, width, height,PositioningMethod.STRETCH,PositioningMethod.STRETCH);
    }

    public UITextInput(){
        this(0,0,0,0,PositioningMethod.STRETCH,PositioningMethod.STRETCH);
    }

    public UITextInput(Color color){
        this(0,0,0,0,PositioningMethod.STRETCH,PositioningMethod.STRETCH, color);
    }

    JComponent createComponent(){
        JTextField field = new JTextField(text);
        //text positioning
        field.setHorizontalAlignment(SwingConstants.LEFT);
        field.setFont(new Font("Lora", Font.PLAIN, 40));
        return field;
    }

    void beforeRefresh(){
        if(component != null)
            text = ((JTextField)component).getText();
    }

    public String getText(){
        text = ((JTextField)component).getText();
        return text;
    }

    void setColor(JComponent component){
        component.setForeground(color);
    }

}
