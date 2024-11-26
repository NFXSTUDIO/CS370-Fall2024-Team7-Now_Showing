package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButton extends UIButton {
    UITextInput searchInput = null;
    public SearchButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color) {
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public void setInputObjects(UITextInput searchInput){
        this.searchInput = searchInput;
    }

    JComponent createComponent() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ViewController.search(searchInput.getText());
            }
        });
        return button;
    }
}
