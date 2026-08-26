package com.attendance.management.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String department;
    private Integer semester;
    public Student() {
    }
    public Student(String name, String email, String phone, String department, Integer semester) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.semester = semester;
}
public Long getId(){
    return id;
}
public void setId(Long id){
    this.id = id;
}
public String getName(){
    return name;
}
public void setName(String name){
    this.name = name;
}
public String getEmail(){
    return email;
}
public void setEmail(String email){
    this.email = email;
}
public String getPhone(){
    return phone;
}
public void setPhone(String phone){
    this.phone = phone;
}
public String getDepartment(){
    return department;
}
public void setDepartment(String department){
    this.department = department;
}
public Integer getSemester(){
    return semester;
}
public void setSemester(Integer semester){
    this.semester = semester;
}
}