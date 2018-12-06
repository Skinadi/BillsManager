import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTransactionPanel extends JPanel implements ActionListener
{
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    private JButton sendTransaction;
    private JButton back;

    public JTextField title;
    private JTextField cost;
    JLabel komunikat;
    User user;
    User friend;
    MainFrame parent;
    public AddTransactionPanel(MainFrame parent,User user, User friend) {
        this.parent=parent;
        this.friend=friend;
        this.user=user;
        sendTransaction = new JButton("Send Transaction");
        back = new JButton("Back");
        komunikat = new JLabel("");
        sendTransaction.addActionListener(this);
        back.addActionListener(this);

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

        if(source == sendTransaction)
        {
            Session session = HibernateUtil.getSession();
            //user = HibernateUtil.findUser(user.getEmail(),session);
            //friend = HibernateUtil.findUser(friend.getEmail(),session);
            session.beginTransaction();
            Transactions transaction1 = new Transactions();
            transaction1.setUser(user);
            transaction1.setFriendsid(friend.getId());
            transaction1.setTitle(title.getText());
            transaction1.setCost(new Integer(cost.getText()));
            transaction1.setStatus(2);
            user.getTransactions().add(transaction1);


            Transactions transaction2 = new Transactions();
            transaction2.setUser(friend);
            transaction2.setFriendsid(user.getId());
            transaction2.setTitle(title.getText());
            transaction2.setCost(new Integer(cost.getText()) * (-1));
            transaction2.setStatus(1);
            friend.getTransactions().add(transaction2);

            session.update(user);
            session.update(friend);
            session.getTransaction().commit();

            session.close();
            parent.updatePanel(new TransactionPanel(parent,user,friend));

        }
        else
        if(source == back)
        {
            Session session = HibernateUtil.getSession();
            parent.updatePanel(new TransactionPanel(parent,user,friend));
            session.close();
        }

    }

    void create()
    {
        JLabel name = new JLabel("Transaction: ");
        JLabel forname = new JLabel("Cost: ");
        title = new JTextField();
        cost = new JTextField();
        komunikat.setVisible(false);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(name);
        inputPanel.add(title);
        inputPanel.add(forname);
        inputPanel.add(cost);
        inputPanel.add(back);
        inputPanel.add(sendTransaction);
        inputPanel.add(komunikat);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);

        this.add(parentPanel);

    }

}
