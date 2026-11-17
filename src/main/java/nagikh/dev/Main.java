package nagikh.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("nagikh.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Movie movie1 = new Movie("first", "drama", 1999);
        Movie movie2 = new Movie("second", "horror", 2000);
        Movie movie3 = new Movie("third", "drama", 1998);

        session.persist(movie1);
        session.persist(movie2);
        session.persist(movie3);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session
                .createQuery("SELECT s FROM Movie s", Movie.class)
                .list().forEach(System.out::println);

        session.createQuery(
                        "SELECT s from Movie s where s.genre = :genre", Movie.class)
                .setParameter("genre", "horror")
                .getResultList().forEach(System.out::println);

        session.beginTransaction();
        Movie movieForUpdate = session.find(Movie.class, 2L);
        session.detach(movieForUpdate);
        movieForUpdate = session.merge(movieForUpdate);
        movieForUpdate.setReleaseYear(2009);
        session.getTransaction().commit();

        session.beginTransaction();
        Movie movieForDelete = session.find(Movie.class, 2L);

        session.remove(movieForDelete);

        session.createQuery("SELECT s from Movie s where s.id != :id", Movie.class)
                .setParameter("id", 2L)
                .getResultList().forEach(System.out::println);

        session.getTransaction().commit();

        session.close();

    }
}