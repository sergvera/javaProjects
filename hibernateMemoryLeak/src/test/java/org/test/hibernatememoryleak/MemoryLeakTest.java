package org.test.hibernatememoryleak;

import junit.framework.TestCase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.test.HibernateUtil;

/**
 * Unit test for simple App.
 */
public class MemoryLeakTest extends TestCase {

    public void testMemoryLeak() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            CourseDao testDao = new CourseDao();

            testDao.setSession(session);

            Long courseId1 = testDao.saveCourse("Physics");

            int i=0;
            while (i<Integer.MAX_VALUE) {
                testDao.updateCourse(courseId1, "Chemistry");

                session.flush();
                session.clear();
            }

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
