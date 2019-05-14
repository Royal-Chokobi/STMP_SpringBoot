package kollus.stmp.stmp.dao;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "customer_info")
@Table(name = "CUSTOMER_INFO")
public class DbCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_index", insertable = false)
    private long customer_index;
    @Column(name="customer_key")
    private String customer_key;
    @Column(name="customer_name")
    private String customer_name;
    @Column(name="customer_email")
    private String customer_email;
    @Basic(optional = false)
    @Column(name = "sysdate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysdate;

    public DbCustomerEntity(){}

    public long getCustomer_index() {
        return customer_index;
    }

    public void setCustomer_index(long customer_index) {
        this.customer_index = customer_index;
    }

    public String getCustomer_key() {
        return customer_key;
    }

    public void setCustomer_key(String customer_key) {
        this.customer_key = customer_key;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    @Override
    public String toString() {
        return "DbCustomerEntity{" +
                "customer_index=" + customer_index +
                ", customer_key='" + customer_key + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", sysdate=" + sysdate +
                '}';
    }
}
