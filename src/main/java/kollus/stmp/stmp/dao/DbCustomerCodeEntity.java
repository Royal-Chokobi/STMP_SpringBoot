package kollus.stmp.stmp.dao;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "customer_code")
@Table(name = "CUSTOMER_CODE")
public class DbCustomerCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code_index;
    @Column(name="customer_key")
    private String customer_key;
    @Column(name="customer")
    private String customer;
    @Basic(optional = false)
    @Column(name = "sysdate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysdate;

    public DbCustomerCodeEntity(){}

    public long getCode_index() {
        return code_index;
    }

    public void setCode_index(long code_index) {
        this.code_index = code_index;
    }

    public String getCustomer_key() {
        return customer_key;
    }

    public void setCustomer_key(String customer_key) {
        this.customer_key = customer_key;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    @Override
    public String toString() {
        return "DbCustomerCodeEntity{" +
                "code_index=" + code_index +
                ", customer_key='" + customer_key + '\'' +
                ", customer='" + customer + '\'' +
                ", sysdate=" + sysdate +
                '}';
    }
}
