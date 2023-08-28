package SpringBootTutorial.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private int years;
    private String url;

    public Product(){}

    public Product( String name, int years, String url) {
        this.name = name;
        this.years = years;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", years=" + years +
                ", url='" + url + '\'' +
                '}';
    }
}
