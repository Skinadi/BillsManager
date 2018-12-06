import jdk.nashorn.internal.runtime.NumberToString;
import org.hibernate.Session;

import javax.swing.*;
import javax.transaction.Transaction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionPanel extends JPanel implements ActionListener
{
    public static final int HEIGHT = 200;
    public static final int WIDTH = 300;
    MainFrame parent;
    User user;
    User friend;
    List<JButton> buttonsList;
    JButton back;
    JButton payeverything;
    JButton newTransaction;
    List <Transactions> mutualTransactions;
    TransactionPanel(MainFrame parent, User user, User friend)
    {
        this.parent=parent;
        this.user = user;
        this.friend = friend;
        buttonsList = new ArrayList<>();
        mutualTransactions = new ArrayList<>();

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Iterator<Transactions> it = user.transactions.iterator();
        Transactions temp;
        while(it.hasNext())
        {
            temp=it.next();
            if(temp!=null && temp.getFriendsid()==friend.getId())
            {
                mutualTransactions.add(temp);
            }
        }
        session.close();
        back = new JButton("Back");
        payeverything = new JButton("Pay everything");
        newTransaction = new JButton("New Transaction");
        back.addActionListener(this);
        payeverything.addActionListener(this);
        newTransaction.addActionListener(this);


        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        create();
        //this.add(payeverything);
        //this.add(back);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == newTransaction)
        {
            parent.updatePanel(new AddTransactionPanel(parent,user,friend));
        }
        else
        if(source== back)
        {
            parent.updatePanel(new UserPanel(parent,user));
        }
        else
        if(source == payeverything)
        {
            //buttonsList.clear();
            //parent.updatePanel(new LoginPanel(parent));
        }
        else
            for(int i = 0; i<buttonsList.size();i++)
            {
                if(source==buttonsList.get(i)) //users
                {

                }
            }

    }
    public void create()
    {
        JPanel friendspanel = new JPanel();

        friendspanel.setLayout(new GridLayout(mutualTransactions.size()+1,3));
        JLabel title;
        JLabel cost;

        for(int i = 0; i<mutualTransactions.size(); i++)
        {
            buttonsList.add(new JButton("Pay"));
        }
        for(int i = 0; i<buttonsList.size();i++)
        {
            title=new JLabel(mutualTransactions.get(i).getTitle());
            cost = new JLabel(new Integer(mutualTransactions.get(i).getCost()).toString());
            friendspanel.add(title);
            friendspanel.add(cost);
            friendspanel.add(buttonsList.get(i));
            buttonsList.get(i).addActionListener(this);
        }
        friendspanel.add(back);
        friendspanel.add(payeverything);
        friendspanel.add(newTransaction);
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(friendspanel, BorderLayout.CENTER);

        this.add(parentPanel);
    }



}
