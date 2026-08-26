package com.attendance.management.service;

import com.attendance.management.entity.Course;
import com.attendance.management.entity.Enrollment;
import com.attendance.management.entity.Student;
import com.attendance.management.repository.CourseRepository;
import com.attendance.management.repository.EnrollmentRepository;
import com.attendance.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enrollStudent(Enrollment enrollment) {

        Long studentId = enrollment.getStudent().getId();
        Long courseId = enrollment.getCourse().getId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}