package com.nowshowing.UI.UIElements.MVC;

import com.nowshowing.UI.UIElements.UIButton;
import com.nowshowing.UI.UIElements.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWatchlistButton extends UIButton {
    public ViewWatchlistButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public JComponent createComponent(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!ViewController.returnToPreviousScene())
                    ViewController.displayWatchlist();
            }
        });
        return button;
    }
}
