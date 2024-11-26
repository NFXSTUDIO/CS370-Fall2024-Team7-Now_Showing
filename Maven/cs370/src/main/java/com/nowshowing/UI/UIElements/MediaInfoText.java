package UIElements;

import wrappers.Media;
import wrappers.Movie;

import javax.swing.*;
import java.awt.*;

public class MediaInfoText extends UIElement{
    //can be static as user is only viewing one piece of media at a time
    static Media displayedMedia;

    public MediaInfoText(float x, float y, float width, float height, PositioningMethod xBehavior, PositioningMethod yBehavior, Color color){
        super(x, y, width, height, xBehavior, yBehavior, color);
    }

    public static void setDisplayedMedia(Media displayedMedia){
        MediaInfoText.displayedMedia = displayedMedia;
    }

    JComponent createComponent(){
        JTextArea label = new JTextArea();
        label.setText(getText());
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Lora", Font.PLAIN, 40));
        return label;
    }

    String getText(){
        Movie movie = (Movie)displayedMedia;
        String text = movie.getTitle();
        text += "\n" + movie.getDirector();
        text += "\n" + movie.getRuntime();
        text += "\n" + movie.getCast();

        return text;
    }

    void setColor(JComponent component){
        component.setForeground(color);
    }
}
