package uz.developers.main;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication extends PostgreSQL94Dialect {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    public MainApplication() {
        super();
        this.registerHibernateType(2003, StringArrayType.class.getName());
    }
}
