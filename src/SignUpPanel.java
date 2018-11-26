/**
 * Created by skinadi on 03.05.18.
 */
/**
 * Created by skinadi on 03.05.18.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUpPanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 200;
    public static final int WIDTH = 300;
    private JButton signup;
    private JButton back;

    public JTextField nameField;
    private JPasswordField passField;
    private JTextField fornameField;
    private JTextField surnameField;
    JLabel komunikat;

    MainFrame parent;
    public SignUpPanel(MainFrame parent) {
        this.parent=parent;
        signup = new JButton("Sign Up");
        back = new JButton("Back");
        komunikat = new JLabel("");
        signup.addActionListener(this);
        back.addActionListener(this);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        create();
    }

    public String getPassword() {
        String password = "";
        char[] pass = passField.getPassword();
        for(int i=0; i<pass.length; i++) {
            password += pass[i];
        }
        return password;
    }

    public String getname(JTextField field)
    {
        return field.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == signup)
        {


        }
        else
        if(source == back)
        {
            parent.updatePanel(new MainPanel(parent));
        }

    }

    void create()
    {
        JLabel name = new JLabel("Login: ");
        JLabel password = new JLabel("Password: ");
        JLabel forname = new JLabel("Forname: ");
        JLabel surname = new JLabel("Surname: ");
        nameField = new JTextField();
        passField = new JPasswordField();
        fornameField = new JTextField();
        surnameField = new JTextField();
        komunikat.setVisible(false);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(name);
        inputPanel.add(nameField);
        inputPanel.add(password);
        inputPanel.add(passField);
        inputPanel.add(forname);
        inputPanel.add(fornameField);
        inputPanel.add(surname);
        inputPanel.add(surnameField);
        inputPanel.add(back);
        inputPanel.add(signup);
        inputPanel.add(komunikat);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);

        this.add(parentPanel);

    }
}
