package UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//the button that brings the player to a different scene
public class SceneSwitchButton extends UIButton{
    int sceneToLoad = 0;

    public SceneSwitchButton(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color, int sceneToOpen){
        super(x, y, width, height, xBehavior, yBehavior, color);
        this.sceneToLoad = sceneToOpen;
    }

    JComponent createComponent(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!ViewController.returnToPreviousScene())
                    ViewController.loadScene(sceneToLoad);
            }
        });
        return button;
    }
}
