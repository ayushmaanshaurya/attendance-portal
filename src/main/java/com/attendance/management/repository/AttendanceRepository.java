package com.attendance.management.repository;
import com.attendance.management.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
 List<Attendance> findByCourseIdAndDateOrderByStudentName(Long courseId, LocalDate date);
 List<Attendance> findByStudentIdOrderByDateDesc(Long studentId);
 Optional<Attendance> findByStudentIdAndCourseIdAndDate(Long studentId, Long courseId, LocalDate date);
 long countByStatus(AttendanceStatus status);
}
