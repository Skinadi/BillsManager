import javax.persistence.*;

@Entity(name = "Transactions")
public class Transactions
{
    Transactions()
    {

    }
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    //private int userid;
    private int friendsid;
    private int cost;
    private String title;
    private int status;

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

}
