package microservices.resourceservice.model;

import javax.persistence.*;

@Entity
@Table (schema = "public", name = "test_data")
public class TestData {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

   private String someData1;
   private String someData2;

    public TestData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSomeData1() {
        return someData1;
    }

    public void setSomeData1(String someData1) {
        this.someData1 = someData1;
    }

    public String getSomeData2() {
        return someData2;
    }

    public void setSomeData2(String someData2) {
        this.someData2 = someData2;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", someData1= " + someData1 + '\'' +
                ", someData2= " + someData2 + '\'' +
                "}";
    }
}
