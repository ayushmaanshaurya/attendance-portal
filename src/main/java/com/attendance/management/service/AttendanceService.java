package com.attendance.management.service;
import com.attendance.management.entity.*;
import com.attendance.management.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
@Service
public class AttendanceService {
 private final AttendanceRepository records; private final StudentRepository students; private final CourseRepository courses;
 public AttendanceService(AttendanceRepository records, StudentRepository students, CourseRepository courses) { this.records=records; this.students=students; this.courses=courses; }
 @Transactional public Attendance mark(Long studentId, Long courseId, LocalDate date, AttendanceStatus status, String note) {
  Student student=students.findById(studentId).orElseThrow(()->new NoSuchElementException("Student not found")); Course course=courses.findById(courseId).orElseThrow(()->new NoSuchElementException("Course not found"));
  Attendance record=records.findByStudentIdAndCourseIdAndDate(studentId,courseId,date).orElseGet(Attendance::new); record.setStudent(student); record.setCourse(course); record.setDate(date); record.setStatus(status); record.setNote(note); return records.save(record);
 }
 public List<Attendance> find(Long courseId, LocalDate date, Long studentId) { if(studentId!=null) return records.findByStudentIdOrderByDateDesc(studentId); if(courseId!=null && date!=null) return records.findByCourseIdAndDateOrderByStudentName(courseId,date); return records.findAll(); }
 public Map<String,Object> dashboard() { long present=records.countByStatus(AttendanceStatus.PRESENT), late=records.countByStatus(AttendanceStatus.LATE), absent=records.countByStatus(AttendanceStatus.ABSENT), total=present+late+absent+records.countByStatus(AttendanceStatus.EXCUSED); Map<String,Object> out=new LinkedHashMap<>(); out.put("students",students.count()); out.put("courses",courses.count()); out.put("records",total); out.put("present",present+late); out.put("attendanceRate",total==0?0:Math.round((present+late)*1000.0/total)/10.0); return out; }
}
