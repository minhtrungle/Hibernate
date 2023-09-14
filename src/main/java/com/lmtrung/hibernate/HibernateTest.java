package com.lmtrung.hibernate;

import com.lmtrung.hibernate.config.ConfigHibernate;
import com.lmtrung.hibernate.pojo.Category;
import com.lmtrung.hibernate.pojo.Manufacturer;
import com.lmtrung.hibernate.pojo.Product;
import org.hibernate.Session;
import org.springframework.security.core.parameters.P;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
        // Nếu thực hiện các lệnh tương tác với dữ liệu đã có như update, delete thì có thể xảy ra tranh chấp dũ liệu ta cần bật giao tác Transaction
//        session.getTransaction().begin();
        // Lưu lại
//        session.save(category);
        // .commit() để chuyển xuống cơ sỡ dữ liệu
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

        // Lấy category ở dòng 2
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
        // Thêm 2 manufacturer
//        mans.add(session.get(Manufacturer.class, 1));
//        mans.add(session.get(Manufacturer.class, 2));
//
//        p.setManufacturers(mans);
//
        // Do khi thực hiện thêm vào bảng product thì đông thời add vào bảng trung gian của product và manufacturer thì cần bật giao tác lên
//        session.getTransaction().begin();
//        session.save(p);
//        session.getTransaction().commit();

        // Cấu hình ManyToMany ngược lại bên Manufacturer
//        Manufacturer m = session.get(Manufacturer.class, 1);
//        m.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

        //// Criteria Query API
        // 4 dòng đầu là build truy vấn
//        CriteriaBuilder builder = (CriteriaBuilder) session.getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//
//        // Có mấy bảng thì tạo nấy root
//        Root<Product> root = query.from(Product.class);
//
//        query = query.select(root);
//
//        // Lấy ra danh sách theo điều kiện thõa mãn không lấy ra tất cả
//        // like() có 2 thứ, đầu tiên là trường nào để dùng so sánh, thứ 2 là giá trị so sánh, lấy ra tên và as là trường này là kiểu chuỗi
//        Predicate predicate1 = builder.like(root.get("name").as(String.class), "%Galaxy%");
//        Predicate predicate2 = builder.like(root.get("name").as(String.class), "%Iphone%");
//        // builder.or để nối 2 predicate bằng mệnh đề or
//        query = query.where(builder.or(predicate1, predicate2));

//
//        // Lấy theo giá lớn hơn bằng và nhỏ hơn bằng một giá cụ thể
//        Predicate predicate3 = builder.greaterThanOrEqualTo(root.get("price").as(BigDecimal.class), new BigDecimal(20000000));
//        Predicate predicate4 = builder.lessThanOrEqualTo(root.get("price").as(BigDecimal.class), new BigDecimal(30000000));
//        query = query.where(builder.and(predicate3, predicate4));
//
//        // Hoặc sử dụng between lấy giá trị ở giữa
//        Predicate predicate5 = builder.between(root.get("price").as(BigDecimal.class), new BigDecimal(20000000), new BigDecimal(30000000));
//        query = query.where(predicate5);

        // Thay vì sử dụng Query q = session.createQuery("FROM Category WHERE id = 1"); //HQL chỉ truy vấn trên lớp Category.java
        // Ta sử dụng
//        Query q = session.createQuery(query);

//
//        List<Product> products = q.getResultList();
//        products.forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

        // Criteria Query API Truy vấn thống kê sử dụng multiselect lấy đúng giá trị muốn lấy sử dụng CriteriaQuery<Object[]>
        CriteriaBuilder builder = (CriteriaBuilder) session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        // Vẫn là Product.class vì đang truy vấn đến Product còn ở trên createQuery là kết quả lấy ra
//        Root root = query.from(Product.class);
//
//        query = query.multiselect(builder.count(root.get("id").as(Integer.class)), builder.max(root.get("price").as(BigDecimal.class)));
//
//        Query q = (Query) session.createQuery(String.valueOf(query));
//
//        Object[] result = (Object[]) q.getSingleResult();
//        System.out.println("Count = " + result[0]);
//        System.out.println("Max = " + result[1]);

        // Gom lại 1 nhóm join 2 table lại dựa trên khóa ngoại
        Root<Product> pRoot = query.from(Product.class);
        Root<Category> cRoot = query.from(Category.class);
        // Lấy khóa ngoại bên Prodcuct so với id bên Category để join 2 bảng
        query.where(builder.equal(pRoot.get("category"), cRoot.get("id")));

        // Truy vấn lấy tên danh mục, đếm số sản phẩm và giá lớn nhất của sản phẩm trong danh mục
        query = query.multiselect(cRoot.get("name").as(String.class),
                builder.count(pRoot.get("id").as(Integer.class)),
                builder.max(pRoot.get("price").as(BigDecimal.class)));
        // Gom nhóm








        query = query.groupBy(cRoot.get("name").as(String.class));
        // Sắp xếp tăng theo trường name
        query = query.orderBy(builder.asc(cRoot.get("name").as(String.class)));

        Query q = (Query) session.createQuery(String.valueOf(query));

        // Lấy ra nhiều kết quả phải đưa vào list
        List<Object[]> result = q.getResultList();
        result.forEach(r -> {

            System.out.printf("%s - count: %d - Max: %.2f\n", r[0], r[1], r[2]);
        });


        session.close();
    }
}
