package nagikh.dev;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("nagikh.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

    }
}