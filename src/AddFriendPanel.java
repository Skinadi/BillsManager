import org.hibernate.Session;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendPanel extends JPanel implements ActionListener
{
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    private JButton addFriend;
    private JButton back;
    private JButton search;
    private JTextField nameField;
    JLabel komentarz;
    Session session;
    MainFrame parent;
    User user;
    User potencialfriend;
    public AddFriendPanel(MainFrame parent, User user) {
        this.user=user;
        this.parent=parent;
        addFriend = new JButton("Add Friend");
        back = new JButton("Back");
        search = new JButton("Search");


        addFriend.addActionListener(this);
        back.addActionListener(this);
        search.addActionListener(this);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        create();

    }

    public String getname(JTextField field)
    {
        return field.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == search)
        {
            session = HibernateUtil.getSession();
            String name = getname(nameField);
            potencialfriend = HibernateUtil.findUser(name,session);
            if(potencialfriend!=null)
            if(name.equals(potencialfriend.getEmail()))
            {
                komentarz.setVisible(true);
            }
            else
            {
                komentarz.setVisible(false);
            }

        }
        else
        if(source == addFriend)
        {
            if(potencialfriend!=null)
            {
                session.beginTransaction();
                //user = HibernateUtil.findUser(user.getEmail(),session);

                user.friends.add(potencialfriend);
                potencialfriend.friends.add(user);
                session.update(user);
                session.update(potencialfriend);
                session.getTransaction().commit();
                session.close();
            }
        }
        else
        if(source == back)
        {
            parent.updatePanel(new UserPanel(parent,user));
            session.close();
        }

    }
    void create()
    {
        JLabel name = new JLabel("Find Friend: ");
        komentarz = new JLabel("Friend found - add him");
        nameField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 3));
        inputPanel.add(name);
        inputPanel.add(nameField);
        inputPanel.add(search);
        inputPanel.add(komentarz);
        inputPanel.add(back);
        inputPanel.add(addFriend);
        komentarz.setVisible(false);
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);

        this.add(parentPanel);

    }
}
