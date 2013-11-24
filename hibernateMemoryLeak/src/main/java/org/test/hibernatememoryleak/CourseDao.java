package org.test.hibernatememoryleak;

import org.hibernate.Session;

/**
 * CourseDao
 *
 */
public class CourseDao {

    private Session session = null;

    public Long saveCourse(String courseName) {

        Long courseId = null;
        Course course = new Course();
        course.setCourseName(courseName);
        courseId = (Long) this.session.save(course);

        return courseId;
    }

    public void updateCourse(Long courseId, String courseName) {

        Course course = (Course) this.session.get(Course.class, courseId);
        course.setCourseName(courseName);

    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

}
