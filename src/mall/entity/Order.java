package mall.entity;

import java.sql.Timestamp;

public class Order {
    private Integer idorder;
    private Integer userid;
    private Timestamp time;
    private Integer commit;
    private String email;

    public Order(Integer idorder, Integer userid, Timestamp time, Integer commit, String email) {
        this.idorder = idorder;
        this.userid = userid;
        this.time = time;
        this.commit = commit;
        this.email = email;
    }

    public Integer getIdorder() {
        return idorder;
    }

    public void setIdorder(Integer idorder) {
        this.idorder = idorder;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getCommit() {
        return commit;
    }

    public void setCommit(Integer commit) {
        this.commit = commit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
