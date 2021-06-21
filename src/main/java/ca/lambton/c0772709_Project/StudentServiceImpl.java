package ca.lambton.c0772709_Project;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		
		this.studentRepository.save(student);
	}

	@Override
	public Student getStudentById(int id) {
		
		Optional<Student> studentOptional = studentRepository.findById(id);
		Student student = null;

		if (studentOptional.isPresent()) {
			student = studentOptional.get();
			
		} else {
			throw new RuntimeException("Student not found with id: " + id);
		}

		return student;
	}

	@Override
	public void deleteStudentByid(int id) {
		
		this.studentRepository.deleteById(id);
	}

	@Override
	public List<Student> getAllStudentsAfterFilter(String value) {
		
		if (value == null || value.equals("_")) {
			return studentRepository.findAll();
			
		} else {
			return studentRepository.findAll().stream()
					.filter(s -> s.getStudentNo().toLowerCase().contains(value.toLowerCase())
							|| s.getFirstName().toLowerCase().contains(value.toLowerCase())
							|| s.getLastName().toLowerCase().contains(value.toLowerCase())
							|| s.getSemester().toLowerCase().contains(value.toLowerCase())
							|| String.valueOf(s.getScore()).contains(value)
							|| String.valueOf(s.getYear()).contains(value))
					.collect(Collectors.toList());
		}
	}
}
