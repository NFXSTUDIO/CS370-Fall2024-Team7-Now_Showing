package com.nowshowing.UI.UIElements.MVC;

import com.nowshowing.UI.UIElements.UIButton;
import com.nowshowing.UI.UIElements.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//the button that appears on the log in screen. clicking attempts to log in the user
public class GetRecommendationButton extends UIButton {
    public GetRecommendationButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color) {
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public JComponent createComponent() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ViewController.requestRecommendation();
            }
        });
        return button;
    }
}