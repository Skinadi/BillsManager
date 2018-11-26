/**
 * Created by skinadi on 03.05.18.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 200;
    public static final int WIDTH = 300;
    private JButton loginButton;
    private JButton signinButton;

    MainFrame parent;
    public MainPanel(MainFrame parent) {
        this.parent = parent;
        loginButton = new JButton("Sign in");
        signinButton = new JButton("Sign up");

        loginButton.addActionListener(this);
        signinButton.addActionListener(this);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(loginButton);
        add(signinButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == loginButton)
        {
            parent.updatePanel(new LoginPanel(parent));
        }

        else
        if(source == signinButton)
        {
            parent.updatePanel(new SignUpPanel(parent));
        }

    }
}