package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//the button that appears on the log in screen. clicking attempts to log in the user
public class LoginButton extends UIButton {
    UITextInput usernameInput = null;
    UITextInput passwordInput = null;
    public LoginButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color) {
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public void setInputObjects(UITextInput usernameInput, UITextInput passwordInput){
        this.usernameInput = usernameInput;
        this.passwordInput = passwordInput;
    }

    JComponent createComponent() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                attemptLogin();
            }
        });
        return button;
    }

    void attemptLogin(){
        System.out.println(usernameInput.getText() + "," + passwordInput.getText());
        ViewController.attemptLogIn(usernameInput.getText(), passwordInput.getText());
    }
}