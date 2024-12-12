package com.nowshowing.UI.UIElements.MVC;

import com.nowshowing.UI.UIElements.UIButton;
import com.nowshowing.UI.UIElements.UITextInput;
import com.nowshowing.UI.UIElements.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//the button that appears on the log in screen. clicking attempts to log in the user
public class RegisterButton extends UIButton {
    UITextInput usernameInput = null;
    UITextInput passwordInput = null;
    public RegisterButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color) {
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
                attemptRegister();
            }
        });
        return button;
    }

    void attemptRegister(){
        System.out.println(usernameInput.getText() + "," + passwordInput.getText());
        ViewController.attemptRegister(usernameInput.getText(), passwordInput.getText());
    }
}