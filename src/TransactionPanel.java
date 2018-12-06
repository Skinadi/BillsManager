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
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
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
            if(temp!=null && temp.getFriendsid()==friend.getId() && temp.getStatus()!=6)
            {
                mutualTransactions.add(temp);
            }
        }
        session.close();
        back = new JButton("Back");
        payeverything = new JButton("Pay everything");
        payeverything.setEnabled(false);
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
                    Session session = HibernateUtil.getSession();
                    Iterator<Transactions> it = user.getTransactions().iterator();
                    Transactions temp = it.next();
                    while(it.hasNext() && temp!=mutualTransactions.get(i))
                    {
                        temp = it.next();
                    }
                    Iterator<Transactions> it1 = friend.getTransactions().iterator();
                    Transactions temp1 = it1.next();
                    while(it1.hasNext() && temp1!=mutualTransactions.get(i))
                    {
                        temp1 = it1.next();
                    }
                    if(temp.getStatus()==1 && temp1.getStatus()==2)
                    {
                        temp.setStatus(3);
                        temp1.setStatus(3);
                    }
                    else
                    if (temp.getStatus()==3 && temp1.getStatus()==3)
                    {
                        temp.setStatus(5);
                        temp1.setStatus(5);
                    }
                    else
                    if(temp.getStatus()==5 && temp1.getStatus()==5)
                    {
                        temp.setStatus(6);
                        temp1.setStatus(6);
                    }
                    session.beginTransaction();
                    session.update(user);
                    session.update(friend);
                    session.getTransaction().commit();
                    session.close();
                    parent.updatePanel(new TransactionPanel(parent,user,friend));
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
            if(mutualTransactions.get(i).getStatus()==1)
                buttonsList.add(new JButton("Accept"));
            else
                if(mutualTransactions.get(i).getStatus()==3)
                    buttonsList.add(new JButton("Send Money"));
                else
                    if(mutualTransactions.get(i).getStatus()==5)
                    {
                        buttonsList.add(new JButton("Accept and Remove"));
                    }
            else
                buttonsList.add(new JButton("boom"));
            if(!mutualTransactions.get(i).getrestatusable())
            {
                buttonsList.get(i).setText("Waiting");
                buttonsList.get(i).setEnabled(false);
            }
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
