package com.lmtrung.hibernate;

import com.lmtrung.hibernate.config.ConfigHibernate;
import com.lmtrung.hibernate.pojo.Category;
import com.lmtrung.hibernate.pojo.Manufacturer;
import com.lmtrung.hibernate.pojo.Product;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateTest {
    public static void main(String[] args) {
        // Mỗi lần truy vấn cần tạo 1 session từ session factory
        Session session = ConfigHibernate.getFACTORY().openSession();

        // Tạo 1 dòng trong csdl
//        Category category = new Category(); // Transient: đối tượng chưa liên kết dòng nào trong csdl
//        category.setName("Máy tính");
//        category.setDescription("Test hibernate");
//        session.save(category);

        // Muốn cập nhật trong csdl cần liên kết đến dòng theo id cần cập nhật
//        Category category = session.get(Category.class, 1); // Persistent
//        category.setDescription("Cặp nhật lại description");
//
//        // Nếu thực hiện các lệnh tương tác với dữ liệu đã có như update, delete thì có thể xảy ra tranh chấp dũ liệu ta cần bật giao tác Transaction
//        session.getTransaction().begin();
//        // Lưu lại
//        session.save(category);
//        // .commit() để chuyển xuống cơ sỡ dữ liệu
//        session.getTransaction().commit();

        // Xóa dữ liệu
//        session.getTransaction().begin();
//        session.delete(category);
//        session.getTransaction().commit();

        // Lấy tất cả các danh mục
//        Query query = session.createQuery("FROM Category"); //HQL chỉ truy vấn trên lớp Category.java
//        // Trả về 1 list
//        List<Category> list = query.getResultList();
//        list.stream().forEach(category -> System.out.printf("%d - %s", category.getId(), category.getName()));

//        Product p = new Product();
//        p.setName("Ipad Pro");
//        p.setPrice(new BigDecimal(22000000));
//
//        // Lấy category ở dòng 2
//        Category c = session.get(Category.class, 2);
//        p.setCategory(c);
//
//        session.save(p);


        // Lấy danh mục muốn tìm sản phẩm
//        Category c = session.get(Category.class, 1);
//        // 2 bảng đã liên kết với nhau sẽ lấy tất cả sản phẩm thuộc danh mục số 1
//        c.getProducts().forEach(p -> System.out.printf("%d - %s", p.getId(), p.getName()));

        // ManyToMany
//        Product p = new Product();
//        p.setName("New Tablet");
//        p.setPrice(new BigDecimal(22222222));
//
//        Category c = session.get(Category.class, 2);
//
//        Set<Manufacturer> mans = new HashSet<>();
//
//        // Thêm 2 manufacturer
//        mans.add(session.get(Manufacturer.class, 1));
//        mans.add(session.get(Manufacturer.class, 2));
//
//        p.setManufacturers(mans);
//

//        // Do khi thực hiện thêm vào bảng product thì đông thời add vào bảng trung gian của product và manufacturer thì cần bật giao tác lên
//        session.getTransaction().begin();
//        session.save(p);
//        session.getTransaction().commit();

        // Cấu hình ManyToMany ngược lại bên Manufacturer
        Manufacturer m = session.get(Manufacturer.class, 1);
        m.getProducts().forEach(p -> System.out.printf("%d - %d", p.getId(), p.getName()));

        session.close();
    }
}