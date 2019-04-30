package kollus.stmp.stmp.dao;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "smtp_reservation_group")
@Table(name = "SMTP_RESERVATION_GROUP")
public class DbGroupReservationEntity {

    @Id
    @Column(name="group_code")
    private String group_code;
    @Column(name="group_title")
    private String group_title;
    @Column(name="reservation_date")
    private Date reservation_date;
    @Column(name="sys_send_date")
    private Date sys_send_date;
    @Column(name="state")
    private String state;
    @Basic(optional = false)
    @Column(name = "sysdate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysdate;

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public Date getSys_send_date() {
        return sys_send_date;
    }

    public void setSys_send_date(Date sys_send_date) {
        this.sys_send_date = sys_send_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    @Override
    public String toString() {
        return "DbGroupReservationEntity{" +
                "group_code='" + group_code + '\'' +
                ", group_title='" + group_title + '\'' +
                ", reservation_date=" + reservation_date +
                ", sys_send_date=" + sys_send_date +
                ", state='" + state + '\'' +
                ", sysdate=" + sysdate +
                '}';
    }
}
