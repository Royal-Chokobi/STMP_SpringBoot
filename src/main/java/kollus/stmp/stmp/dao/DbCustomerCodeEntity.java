package kollus.stmp.stmp.dao;

import javax.persistence.*;

@Entity(name = "customer_code")
@Table(name = "CUSTOMER_CODE")
public class DbCustomerCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long index;
    @Column(name="customer_key")
    private String customer_key;
    @Column(name="customer")
    private String customer;
    @Column(name="sysdate")
    private String sysdate;

    public DbCustomerCodeEntity(){}

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
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

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    @Override
    public String toString() {
        return "DbCustomerCodeEntity{" +
                "index=" + index +
                ", customer_key='" + customer_key + '\'' +
                ", customer='" + customer + '\'' +
                ", sysdate='" + sysdate + '\'' +
                '}';
    }
}
