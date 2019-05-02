package kollus.stmp.stmp.dao;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "smtp_reservation")
@Table(name = "SMTP_RESERVATION")
public class DbReservationEntity {

    @Id
    @Column(name="reservation_code")
    private String reservation_code;
    @Column(name="group_code")
    private String group_code;
    @Column(name="customer")
    private String customer;
    @Column(name="email_title")
    private String email_title;
    @Column(name="email_form")
    private String email_form;
    @Column(name="customer_address")
    private String customer_address;
    @Column(name="sys_send_date")
    private Date sys_send_date;
    @Column(name="state")
    private String state;
    @Basic(optional = false)
    @Column(name = "sysdate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysdate;

    public DbReservationEntity(){}

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getReservation_code() {
        return reservation_code;
    }

    public void setReservation_code(String reservation_code) {
        this.reservation_code = reservation_code;
    }

    public String getEmail_title() {
        return email_title;
    }

    public void setEmail_title(String email_title) {
        this.email_title = email_title;
    }

    public String getEmail_form() {
        return email_form;
    }

    public void setEmail_form(String email_form) {
        this.email_form = email_form;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
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

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    @Override
    public String toString() {
        return "DbReservationEntity{" +
                "reservation_code='" + reservation_code + '\'' +
                ", group_code='" + group_code + '\'' +
                ", customer='" + customer + '\'' +
                ", email_title='" + email_title + '\'' +
                ", email_form='" + email_form + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", sys_send_date=" + sys_send_date +
                ", state='" + state + '\'' +
                ", sysdate=" + sysdate +
                '}';
    }
}
