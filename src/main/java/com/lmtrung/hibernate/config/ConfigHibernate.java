package com.lmtrung.hibernate.config;

import com.lmtrung.hibernate.pojo.Category;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class ConfigHibernate {

    private static final SessionFactory FACTORY;

    // Khai báo khối tĩnh được gọi lần đầu khi lớp được nạp lên và chỉ chạy 1 lần duy nhất và tạo đói tượng duy nhất
    static {
        Configuration configuration = new Configuration();

//        // Config theo file xml và thay thế cho ở dưới
//        configuration.configure("hibernate.cfg.xml");

        // Cấu hình các properties
        Properties properties = new Properties();
        // "Dialect” để biết được loại database nào đang được sử dụng
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        // Thực thi câu truy vấn thì code java sẽ show sql
        properties.put(Environment.SHOW_SQL, "true");


        configuration.setProperties(properties);
        // Cho đối tượng cấu hình biết có persistent class
        configuration.addAnnotatedClass(Category.class);

        // registry cung cấp các thông tin cho factory để xây dựng factory
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        // Từ đối tượng configuration tạo FACTORY
        FACTORY = configuration.buildSessionFactory(registry);
    }
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
