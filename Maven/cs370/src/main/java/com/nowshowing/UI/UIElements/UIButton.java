package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;

public class UIButton extends UIElement{
    public UIButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public UIButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior){
        super(x, y, width, height, xBehavior, yBehavior);
    }

    public UIButton(float x, float y, float width, float height){
        super(x, y, width, height);
    }

    JComponent createComponent(){
        return new JButton();
    }

}
