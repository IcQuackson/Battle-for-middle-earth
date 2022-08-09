package gui;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.UIManager;


public class ResizeToTextButton extends JButton {
    
    public ResizeToTextButton(String txt){
        super(txt);
        setText(txt);
    }

    // Resizes button to the size of its text
    @Override
    public void setText(String arg0) {
        super.setText(arg0);
        FontMetrics metrics = getFontMetrics(UIManager.getDefaults().getFont("Label.font")); 
        int width = metrics.stringWidth( getText() );
        int height = metrics.getHeight();
        Dimension newDimension =  new Dimension(width+40,height+10);
        setPreferredSize(newDimension);
        setBounds(new Rectangle(
                    getLocation(), getPreferredSize()));
    }
}
