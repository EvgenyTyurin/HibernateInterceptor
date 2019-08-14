package evgenyt.springdemo;

import org.hibernate.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hibernate interceptor sample
 * MyInterceptor class implements Interceptor interface, and connects to Session using
 * SessionFactory.withOptions(MyInterceptor).openSession() method
 *
 * Aug 2019 EvgenyT
 */

public class App {

    public static void main( String[] args ) {
        // Load context
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        // Loaf session factory bean
        SessionFactory sessionFactory = context.getBean("sessionFactory",
                SessionFactory.class);
        // Create session with interceptor attached
        try (Session session = sessionFactory.withOptions().
                interceptor(new MyInterceptor()).openSession()){
            // Fire some triggers
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
