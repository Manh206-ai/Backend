package com.example.Student.Service.service;

import com.example.Student.Service.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    // Chưa dùng Database -> lưu tạm bằng Map trong bộ nhớ
    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public StudentService() {
        // Vài dữ liệu mẫu để test GET ngay khi vừa chạy app
        add(new Student(null, "Nguyen Van An", "2003-05-12", "Nam", "Ha Noi", "Cong nghe thong tin"));
        add(new Student(null, "Tran Thi Binh", "2003-09-20", "Nu", "Hai Phong", "Ke toan"));
    }

    public List<Student> getAll() {
        return List.copyOf(students.values());
    }

    public Student getById(Long id) {
        return students.get(id);
    }

    public Student add(Student student) {
        Long newId = idCounter.incrementAndGet();
        student.setStudentId(newId);
        students.put(newId, student);
        return student;
    }

    public Student update(Long id, Student updated) {
        Student existing = students.get(id);
        if (existing == null) {
            return null;
        }
        existing.setFullName(updated.getFullName());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setGender(updated.getGender());
        existing.setHometown(updated.getHometown());
        existing.setMajor(updated.getMajor());
        return existing;
    }

    public boolean delete(Long id) {
        return students.remove(id) != null;
    }
}