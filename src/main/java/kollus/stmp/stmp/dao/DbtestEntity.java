package kollus.stmp.stmp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "test_table")
@Table(name = "TEST_TABLE")
public class DbtestEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   // private Long seq;
    @Column(name="test1")
    private int test1;
    @Column(name="test2")
    private String test2;
    @Column(name="test3")
    private String test3;

    public DbtestEntity(){}

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }

    @Override
    public String toString() {
        return "DbtestEntity{" +
                " test1=" + test1 +
                ", test2='" + test2 + '\'' +
                ", test3='" + test3 + '\'' +
                '}';
    }
}
