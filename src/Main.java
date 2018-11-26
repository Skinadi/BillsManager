import java.awt.EventQueue;
public class Main {

    public static void main(String[] args)
    {
        try
        {
            HibernateUtil.run();
        }
        catch (java.lang.Exception e)
        {}
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

}