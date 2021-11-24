import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentMain {

	public static void main(String[] args) {
		Student student = new Student("Ramesh", "Fadatare", "rameshfadatare@javaguides.com");
        Student student1 = new Student("John", "Cena", "john@javaguides.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
        	System.out.println("before transact : ");
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            System.out.println("saved");
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            System.out.println("select: " + students.size());
            students.forEach(s -> System.out.println(s.getFirstName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        HibernateUtil.shutdown();
    }
}

	