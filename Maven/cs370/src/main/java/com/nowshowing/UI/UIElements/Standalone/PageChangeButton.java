package com.nowshowing.UI.UIElements.Standalone;

import com.nowshowing.UI.UIElements.MVC.ListLabel;
import com.nowshowing.UI.UIElements.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageChangeButton extends UIButton {
    ListLabel listLabel;
    int change; //amount to change page by

    public PageChangeButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color, int change) {
        super(x, y, width, height, xBehavior, yBehavior, color);
        this.change = change;
    }

    public void setListLabel(ListLabel listLabel) {
        this.listLabel = listLabel;
    }

    public JComponent createComponent() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                listLabel.changePage(change);
            }
        });
        return button;
    }
}
