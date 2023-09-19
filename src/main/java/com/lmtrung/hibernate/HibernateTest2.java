package com.lmtrung.hibernate;

import com.lmtrung.hibernate.config.ConfigHibernate;
import com.lmtrung.hibernate.pojo.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
public class HibernateTest2 {
    public static void main(String[] args) {
        Session session = ConfigHibernate.getFACTORY().openSession();

        // Một số trường hợp sử dụng FROM, WHERE, ORDER BY
        // Phải là lớp đối tượng chứ không phải bảng trong csdl
        Query q = (Query) session.createQuery("FROM Product");
        List<Product> products = q.getResultList();
        products.forEach(p -> System.out.printf("%d - %s - %.2f\n", p.getId(), p.getName(), p.getPrice()));

        // Lấy theo cụ thể một trường trong lớp đối tượng thì kết quả trả về danh sách mỗi kết quả là object array
        Query q1 = (Query) session.createQuery("SELECT P.id, P.name, P.price FROM Product P");
        List<Object[]> products1 = q1.getResultList();
        // Cú pháp P.id =: id sau đó mới truyền giá trị sử dụng bằng setParameter
        Query q2 = (Query) session.createQuery("SELECT P.id, P.name, P.price FROM Product P WHERE P.id =: id ORDER BY P.id DESC"); // tương tự sử dụng : với LIKE:
        q2.setParameter("id", 1);
        List<Object[]> products2 = q2.getResultList();








        products2.forEach(p -> System.out.printf("%d - %s - %.2f\n", p[0], p[1], p[2]));

        // Thống kê thông tin sản phẩm
        Query q3 = (Query) session.createQuery("SELECT C.name, Count(P.id), Max(P.price), Min(P.price) " +
                "FROM Product P RIGHT OUTER JOIN Category ON P.category = C.id" +
                "GROUP BY C.name");
        List<Object[]> results = q3.getResultList();
        results.forEach(obj -> System.out.printf("%s: Cound: %d; Max: %.2f; Min: %.2f \n", obj[0], obj[1], obj[2], obj[3]));


        // I
        // SERT, UPDATE, DELETE tương tự nhau
        Query q4 = (Query) session.createQuery("UPDATE Category C SET C.name =: name WHERE C.id =: id");
        q4.setParameter("name", "Smart Watch");


        q4.setParameter("id", 30);
        // Update cần phải bật giao tác lên
        session.getTransaction().begin();
        q4.executeUpdate();
        session.getTransaction().commit();

        session.close();
    }
}
