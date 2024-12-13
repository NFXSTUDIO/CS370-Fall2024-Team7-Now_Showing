package com.nowshowing.UI.UIElements.Standalone;

import com.nowshowing.UI.UIElements.MVC.ListLabel;
import com.nowshowing.UI.UIElements.UIButton;
import com.nowshowing.UI.UIElements.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToWatchlistButton extends UIButton {
    ListLabel listLabel;

    public AddToWatchlistButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color) {
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public void setListLabel(ListLabel listLabel) {
        this.listLabel = listLabel;
    }

    public JComponent createComponent() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ViewController.addToWatchlist(ListLabel.getDisplayedMedia());
                System.out.println("added to watchlist");
            }
        });
        return button;
    }
}
