import javax.persistence.*;
import java.util.Set;

@Entity(name = "Transactions")
public class Transactions
{
    Transactions()
    {

    }
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name = "MutualTransactions" ,
            joinColumns = { @JoinColumn(name = "firstid")},
            inverseJoinColumns = { @JoinColumn(name = "secondid") }
    )
    Transactions mutualtransaction;
    //private int userid;
    private int friendsid;
    private int cost;
    private String title;
    private int status;

    boolean getrestatusable()
    {
        if(status==1 || (status==3 && cost>=0) || (status==5 && cost<0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFriendsid() {
        return friendsid;
    }

    public void setFriendsid(int friendsid) {
        this.friendsid = friendsid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transactions getMutualtransaction() {
        return mutualtransaction;
    }

    public void setMutualtransaction(Transactions mutualtransaction) {
        this.mutualtransaction = mutualtransaction;
    }

}
