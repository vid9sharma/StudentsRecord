package ca.lambton.c0772709_Project;

import java.util.List;

public interface StudentService {

	List<Student> getAllStudents();

	void saveStudent(Student student);

	Student getStudentById(int id);

	void deleteStudentByid(int id);
	
	List<Student> getAllStudentsAfterFilter(String value);
}
