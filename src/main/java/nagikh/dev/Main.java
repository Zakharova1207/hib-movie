package nagikh.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("nagikh.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User("Alice");
        session.persist(user);
        UserProfile profile = new UserProfile("Hello world", LocalDateTime.now(), user);
        session.persist(profile);
        User user2 = new User("Mama");
        session.persist(user2);
        User user3 = new User("Mama");
        session.persist(user3);
        User user4 = new User("Mama");
        session.persist(user4);
        UserProfile profile2 = new UserProfile("papa", LocalDateTime.now(), user2);
        UserProfile profile3 = new UserProfile("papa", LocalDateTime.now(), user4);
        session.persist(profile2);
        session.persist(profile3);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        System.out.println(session.find(UserProfile.class, 1L));
        session.close();

        session = sessionFactory.openSession();
        System.out.println(session.find(User.class, 4L));
        session.close();

        try {
            sessionFactory.openSession();
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
        }
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(profile);
        session.remove(user);
        System.out.println(session.find(User.class, 1L));
        session.getTransaction().commit();
        session.close();

    }
}
