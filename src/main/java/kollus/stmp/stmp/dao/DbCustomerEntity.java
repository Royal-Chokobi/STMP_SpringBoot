package kollus.stmp.stmp.dao;

import javax.persistence.*;

@Entity(name = "customer_info")
@Table(name = "CUSTOMER_INFO")
public class DbCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long index;
    @Column(name="customer_key")
    private String customer_key;
    @Column(name="customer_name")
    private String customer_name;
    @Column(name="customer_email")
    private String customer_email;

    public DbCustomerEntity(){}

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

    @Override
    public String toString() {
        return "DbCustomerEntity{" +
                "index=" + index +
                ", customer_key='" + customer_key + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_email='" + customer_email + '\'' +
                '}';
    }
}
