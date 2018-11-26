import javax.swing.*;

/**
 * Created by skinadi on 03.05.18.
 */
public class MainFrame extends JFrame {
    JPanel currPanel;
    MainFrame()
    {
        super("Rozlicz się");

        currPanel = new MainPanel(this);
        add(currPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    void updatePanel(JPanel newPanel)
    {
        remove(currPanel);
        add(newPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        update(this.getGraphics());
        currPanel = newPanel;
    }
}
