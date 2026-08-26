package com.attendance.management.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "attendance_records", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id", "attendance_date"}))
public class Attendance {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "student_id", nullable = false) @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) private Student student;
 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "course_id", nullable = false) @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) private Course course;
 @Column(name = "attendance_date", nullable = false) private LocalDate date;
 @Enumerated(EnumType.STRING) @Column(nullable = false) private AttendanceStatus status;
 private String note;
 public Long getId() { return id; } public Student getStudent() { return student; } public void setStudent(Student value) { student = value; }
 public Course getCourse() { return course; } public void setCourse(Course value) { course = value; }
 public LocalDate getDate() { return date; } public void setDate(LocalDate value) { date = value; }
 public AttendanceStatus getStatus() { return status; } public void setStatus(AttendanceStatus value) { status = value; }
 public String getNote() { return note; } public void setNote(String value) { note = value; }
}
