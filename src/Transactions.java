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

    private int userid;
    private int friendsid;
    private int cost;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

}
