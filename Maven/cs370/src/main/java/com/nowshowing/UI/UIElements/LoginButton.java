package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//the button that brings the player to the login scene
public class LoginButton extends UIButton{
    public LoginButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    JComponent createComponent(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ViewController.loadScene(ViewController.LOGIN_SCENE);
            }
        });
        return button;
    }
}
