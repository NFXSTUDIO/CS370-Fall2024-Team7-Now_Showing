package com.nowshowing.UI;

import com.nowshowing.UI.UIElements.ViewController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ViewController viewController = new ViewController();
        viewController.loadScene(ViewController.LANDING_SCENE);

    }

}