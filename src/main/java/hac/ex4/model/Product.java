package hac.ex4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    private String src = "https://islandpress.org/sites/default/files/default_book_cover_2015.jpg";

    @Column()
    @Min(0)
    private int quantity;

    @Column()
    @Min(0)
    private double price;

    @Column()
    @Min(0)
    private double discount;

    public Product() {
    }

    public Product(long id, @NotEmpty String name, String src, @Min(0) int quantity, @Min(0) double price, @Min(0) double discount) {
        this.id = id;
        this.name = name;
        this.src = src;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}

