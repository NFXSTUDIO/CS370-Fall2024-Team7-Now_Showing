package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;

public class UIText extends UIElement{
    String text;

    public UIText(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color,String text){
        super(x, y, width, height, xBehavior, yBehavior, color);
        this.text = text;
    }

    public UIText(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior,String text){
        this(x, y, width, height, xBehavior, yBehavior, new Color(0x0000), text);
    }

    public UIText(float x, float y, float width, float height,String text){
        this(x, y, width, height,PositioningMethod.STRETCH,PositioningMethod.STRETCH, text);
    }

    public UIText(String text){
        this(0,0,0,0,PositioningMethod.STRETCH,PositioningMethod.STRETCH, text);
    }

    public UIText(String text, Color color){
        this(0,0,0,0,PositioningMethod.STRETCH,PositioningMethod.STRETCH, color, text);
    }

    public void setText(String text){
        this.text = text;
    }

    JComponent createComponent(){
        JLabel label = new JLabel();
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Lora", Font.PLAIN, 40));
        return label;
    }

    void setColor(JComponent component){
        component.setForeground(color);
    }

}
