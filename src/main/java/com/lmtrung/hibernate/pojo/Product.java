package com.lmtrung.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    // Trên trùng column nên không cần khai báo
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE) // Lưu trữ ngày tháng
    private Date createDate;
    private boolean active;
    // Liên kết khóa ngoại, nhiều sản phẩm thuộc 1 mục
    // Không khai báo sẽ lấy tham số cực đỉnh, tham số quan trọng là: fetch (Mỗi lần lấy product sẽ tự động lấy category cho dù có sử dụng hay không)
    // Nếu FetchType.LAZY khi lấy product sẽ k lấy category khi nào đụng tới nó mới lấy
    // Mặc định là EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_ìd")
    private Category category; // Khóa ngoại phải khai báo theo object

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
