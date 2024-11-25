package com.nowshowing.UI.UIElements;

import javax.swing.*;
import java.awt.*;
//these 2 are for resize detection
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NowShowingFrame {
    JFrame frame;

    Scene activeScene = null;

    public NowShowingFrame(){
        this(800, 640);
    }

    public NowShowingFrame(int width, int height){
        frame = new JFrame("Now Showing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        frame.setLayout(null);

        //add listener to frame so it can detect window resize
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //this causes the scene to refresh any time the window is resized
                if(activeScene != null)
                    activeScene.refresh();
            }
        });
    }

    public void setScene(Scene scene){
        activeScene = scene;
        scene.addToFrame(this);
    }

    public void add(JComponent component){
        frame.getContentPane().add(component);
    }

    public void refresh(){
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    public void clear(){
        //System.out.println(frame.getContentPane().getComponents().length);
        frame.getContentPane().removeAll();
    }

    public int getWidth(){
        return frame.getContentPane().getWidth();
    }

    public int getHeight(){
        return frame.getContentPane().getHeight();
    }

    public void setSize(int x, int y){
        frame.getContentPane().setPreferredSize(new Dimension(x, y));
        frame.pack();
    }

    public Dimension getSize(){
        return frame.getContentPane().getSize();
    }

}
