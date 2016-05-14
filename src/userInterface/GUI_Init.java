package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * Slider code modified off: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanewithaJSlider.htm
 * Textfield code modified off http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#input
 */
public class GUI_Init 
{	
	//Testing main stub
	public static void main(String[] args)
	{
		System.out.println(getNumPlayers());
		String s = getPlayerName();
		System.out.println(s);
		System.out.println(getStartingTerritory(s));
	}	
	public static int getNumPlayers()//slider from 2 through 5, default 2
	{	
		JFrame parent = new JFrame();

	    JOptionPane optionPane = new JOptionPane();
	    JSlider slider = getSlider(optionPane);
	    optionPane.setMessage(new Object[] { "How many players will be playing?", slider });
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
	    JDialog dialog = optionPane.createDialog(parent, "Rish Set-Up");
	    dialog.setVisible(true);
	    return (Integer) slider.getValue();
	}
	
	private static JSlider getSlider(final JOptionPane dialog)
	{
		JSlider slider = new JSlider(2,5);	
		slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    ChangeListener changeListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent changeEvent) {
	          JSlider theSlider = (JSlider) changeEvent.getSource();
	          if (!theSlider.getValueIsAdjusting()) {
	            dialog.setInputValue(new Integer(theSlider.getValue()));
	          }
	        }
	      };
	    return slider;
	}
	//TODO: Still no error handling
	public static String getPlayerName()
	{
		JFrame parent = new JFrame();
		String s = 
				(String) JOptionPane.showInputDialog(parent, "Enter player name", "Player");
		return s;
	}
	
	public static String getStartingTerritory(String playerName)
	{
		JFrame parent = new JFrame();
		String s = 
				(String) JOptionPane.showInputDialog(parent, "Enter Starting territory, " + playerName, "Territory");
		return s;
	}
	


}	
