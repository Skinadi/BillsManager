import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.metamodel.EntityType;

public class Tests {
    @Test
    public void isgood()
    {
        Assert.assertFalse(test());
        //Assert.assertTrue(test());
        addUser("bialasdamian@lol.pl","Damian","Białas","trololo");
        getUsers();
    }
    public void addfriend(User user1, User user2)
    {
        final Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            user1=findUser("lol",session);
            user2=findUser("lil",session);
            user1.friends.add(user2);
            //user2.friends.add(user1);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }


    }
    public User findUser(String email1,Session session)
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
    @Test
    public void checkiffriendexistsafterexit()
    {
        addfriend(addUser("lol","Krystian","Koks","silnehaslo"),
                addUser("lil","Piotr","Zupa","moc"));
        //addUser("lol","gog","sds","sdasd");
        final Session session = HibernateUtil.getSession();
        try {
            //String foo = "FROM User E where E.email like 'lol'";
            final Query query = session.createQuery("FROM User");
            System.out.println("executing: " + query.getQueryString());
            for (Object o : query.list()) {
                System.out.println("  " + o.toString());
                System.out.println(((User)(o)).friends.size());
            }
        } finally {
            session.close();
        }

    }
    public User addUser(String email,String forname, String surname, String password) {
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
    public void getUsers()
    {
        final Session session = HibernateUtil.getSession();
        try {
            String foo = "FROM User";
            final Query query = session.createQuery(foo);
            System.out.println("executing: " + query.getQueryString());
            for (Object o : query.list()) {
                    System.out.println("  " + o.toString());
            }
        } finally {
            session.close();
        }
    }
    public boolean test()
    {
        return false;
    }
}
