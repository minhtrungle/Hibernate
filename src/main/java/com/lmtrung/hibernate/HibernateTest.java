package com.lmtrung.hibernate;

import com.lmtrung.hibernate.config.ConfigHibernate;
import com.lmtrung.hibernate.pojo.Category;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.List;

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
        Query query = session.createQuery("FROM Category"); //HQL chỉ truy vấn trên lớp Category.java
        // Trả về 1 list
        List<Category> list = query.getResultList();
        list.stream().forEach(category -> System.out.printf("%d - %s", category.getId(), category.getName()));

        session.close();
    }
}
