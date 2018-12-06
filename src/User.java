import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Users")
public class User
{
    public User()
    {

    }
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    private String forname;

    private String surname;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinTable(
            name = "Friends" ,
            joinColumns = { @JoinColumn(name = "id")},
            inverseJoinColumns = { @JoinColumn(name = "friendid") }
    )

    Set<User> friends = new HashSet<>();



    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
   /* @JoinTable(
            name = "Transactions" ,
            joinColumns = { @JoinColumn(name = "userid")}
    )*/
    //@JoinColumn(name = "userid")
    Set<Transactions> transactions = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return forname+" "+surname+" "+email+" "+password;
    }
}
