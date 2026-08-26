package com.attendance.management.controller;
import com.attendance.management.entity.*;
import com.attendance.management.service.AttendanceService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
@RestController @RequestMapping("/api")
public class AttendanceController {
 private final AttendanceService service; public AttendanceController(AttendanceService service) { this.service=service; }
 @GetMapping("/dashboard") public Map<String,Object> dashboard() { return service.dashboard(); }
 @GetMapping("/attendance") public List<Attendance> records(@RequestParam(required=false) Long courseId,@RequestParam(required=false) LocalDate date,@RequestParam(required=false) Long studentId) { return service.find(courseId,date,studentId); }
 @PostMapping("/attendance") public ResponseEntity<Attendance> mark(@RequestBody Map<String,String> data) { return ResponseEntity.status(HttpStatus.CREATED).body(service.mark(Long.valueOf(data.get("studentId")),Long.valueOf(data.get("courseId")),LocalDate.parse(data.getOrDefault("date",LocalDate.now().toString())),AttendanceStatus.valueOf(data.getOrDefault("status","PRESENT")),data.get("note"))); }
}
