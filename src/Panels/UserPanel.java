package Panels;

import Account.HibernateUtil;
import Panels.AddFriendPanel;
import Panels.LoginPanel;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Panels.*;
import Account.*;
import App.*;

/**
 * Created by skinadi on 03.05.18.
 */
public class UserPanel extends JPanel implements ActionListener {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    MainFrame parent;
    User user;
    List <JButton> buttonsList;
    JButton addfriend;
    JButton logout;
    UserPanel(MainFrame parent, User user)
    {
        this.parent=parent;
        Session session = HibernateUtil.getSession();
        this.user = HibernateUtil.findUser(user.getEmail(),session);
        session.close();
        buttonsList = new ArrayList<>();

        addfriend = new JButton("Add friend");
        logout = new JButton("Logout");
        addfriend.addActionListener(this);
        logout.addActionListener(this);


        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        create();
        //this.add(payeverything);
        //this.add(back);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==addfriend)
        {
            parent.updatePanel(new AddFriendPanel(parent,user));
        }
        else
        if(source==logout)
        {
            buttonsList.clear();
            parent.updatePanel(new LoginPanel(parent));
        }
        else
        for(int i = 0; i<buttonsList.size();i++)
        {
            if(source==buttonsList.get(i)) //users
            {
                Session session = HibernateUtil.getSession();
                Iterator<User> it = user.getFriends().iterator();
                for(int j = 0; j<i; j++)
                {
                    it.next();
                }
                parent.updatePanel(new TransactionPanel(parent,user,it.next()));
                session.close();
            }
        }

    }
    public void create()
    {
        JPanel friendspanel = new JPanel();
        for(int i = 0; i<user.getFriends().size();i++)
        {
            buttonsList.add(new JButton("Rozlicz się !"));
        }
        friendspanel.setLayout(new GridLayout(buttonsList.size()+1,2));
        JLabel name;
        Iterator<User> it = user.getFriends().iterator();
        for(int i = 0; i<buttonsList.size();i++)
        {
            name=new JLabel(it.next().getForname());
            friendspanel.add(name);
            friendspanel.add(buttonsList.get(i));
            buttonsList.get(i).addActionListener(this);
        }
        friendspanel.add(logout);
        friendspanel.add(addfriend);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(friendspanel, BorderLayout.CENTER);

        this.add(parentPanel);
    }
}
