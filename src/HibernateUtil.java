import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Transactions.class);
            configuration.addAnnotatedClass(User.class);
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void run() throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            /*session.beginTransaction();
            User user1 = new User();
            user1.setEmail("lalal");
            user1.setForname("Damian");
            user1.setSurname("Białas");
            user1.setPassword("lulu");
            user1.friends.add(user1);
            session.save(user1);
            session.getTransaction().commit();
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*/
        } finally {
            session.close();
        }
    }
    public static User findUser(String email1,Session session)
    {
        try {
            String foo = "FROM User E where E.email like :email2";
            final Query query = session.createQuery(foo);
            query.setParameter("email2",email1);
            //System.out.println("executing: " + query.getQueryString());
            if(query.list().size()!=0)
                return (User)query.list().get(0);
            else
                return null;
        } finally {

        }
    }
    public static User addUser(String email,String forname, String surname, String password) {
        final Session session = HibernateUtil.getSession();
        User user1 = new User();
        try {
            session.beginTransaction();
            user1.setEmail(email);
            user1.setForname(forname);
            user1.setSurname(surname);
            user1.setPassword(password);
            session.save(user1);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return user1;
    }
}
