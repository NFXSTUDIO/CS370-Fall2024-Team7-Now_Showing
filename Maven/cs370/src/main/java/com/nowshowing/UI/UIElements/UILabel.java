package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;

public class UILabel extends UIElement{
    public UILabel(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public UILabel(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior){
        super(x, y, width, height, xBehavior, yBehavior);
    }

    public UILabel(float x, float y, float width, float height){
        super(x, y, width, height);
    }

    JComponent createComponent(){
        return new JLabel();
    }

}
