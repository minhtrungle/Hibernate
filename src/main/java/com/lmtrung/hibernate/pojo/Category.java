package com.lmtrung.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

// Các Persistent Class
@Entity
@Table(name = "category")
// Các POJO nên sử dụng Serializable có trách nhiệm khi ta gửi sữ liệu ra môi trường internet thì Serializable đồng bộ dữ liệu ra bên ngoài
// còn khi trở về hệ thống thì thực hiện chuyển dữ liệu trên máy xử lý được
public class Category implements Serializable {

    // Khóa chính
    // Các Column đc khai báo trùng tên trường trong csdl thì bỏ k cần thêm @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Trường tự tăng
    // @Column(name = "id")
    private int id;





    // @Column(name = "name", length = 100, nullable = false)
    private String name;

    // @Column(name = "description")
    private String description;

    // ManyToOne mặc định EAGER còn OneToMany mặc định LAZY
    @OneToMany(mappedBy = "category") // Cấu hình ngược lại thì Thuộc tính Category khai báo bên Product liên lạc với mappedBy
    private Set<Product> products; // 2 sản phẩm không thể trùng nhau thuộc cùng 1 danh sách category

    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category() {
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}