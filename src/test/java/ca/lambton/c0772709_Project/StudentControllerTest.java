package ca.lambton.c0772709_Project;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ApplicationContext context;

	@MockBean
	private StudentService studentService;
	@MockBean
	private PageHitCount pageHitCount;

	// test for welcome page
	@Test
	public void homePageTest() throws Exception {
		this.mockMvc.perform(get("/"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("welcome"));
	}

	// test for contact page
	@Test
	public void contactPageTest() throws Exception {
		this.mockMvc.perform(get("/contact"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("contact"));
	}

	// test for studentList page
	@Test
	public void listOfStudentsTest() throws Exception {
		List<Student> listOfStudents = new ArrayList<Student>();
		listOfStudents.add(Student.builder()
									.id(1)
									.firstName("Vidhu")
									.lastName("Sharma")
									.semester("3rd")
									.year(2021)
									.score(89)
									.build());
		listOfStudents.add(Student.builder()
									.id(2)
									.firstName("William")
									.lastName("James")
									.semester("1st")
									.year(2002)
									.score(91)
									.build());

		Mockito.when(studentService.getAllStudents())
				.thenReturn(listOfStudents);

		StudentService studentServiceFromContext = context.getBean(StudentService.class);
		List<Student> studentList = studentServiceFromContext.getAllStudents();

		this.mockMvc.perform(get("/studentlist"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("studentList"))
					.andExpect(model().attribute("studentList", studentList));
	}

	// test for studentList page after filter
	@Test
	public void listOfStudentsAfterFilterTest() throws Exception {
		String filterValue = "2021";
		
		List<Student> listOfStudents = new ArrayList<Student>();
		listOfStudents.add(Student.builder()
									.id(1)
									.firstName("Vidhu")
									.lastName("Sharma")
									.semester("3rd")
									.year(2021)
									.score(89)
									.build());
		listOfStudents.add(Student.builder()
									.id(2)
									.firstName("William")
									.lastName("James")
									.semester("1st")
									.year(2002)
									.score(91)
									.build());
		listOfStudents.add(Student.builder()
									.id(3)
									.firstName("Lucy")
									.lastName("Grey")
									.semester("2nd")
									.year(2021)
									.score(95)
									.build());

		Mockito.when(studentService.getAllStudents())
				.thenReturn(listOfStudents);

		StudentService studentServiceFromContext = context.getBean(StudentService.class);
		List<Student> studentList = studentServiceFromContext.getAllStudentsAfterFilter(filterValue);

		this.mockMvc.perform(get("/studentlist?searchFilter=" + filterValue))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("studentList"))
					.andExpect(model().attribute("studentList", studentList));
	}

	// test for GET add page when adding a new student
	@Test
	public void addStudentGetTest() throws Exception {
		Student student = new Student();

		this.mockMvc.perform(get("/addStudent"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("add"))
					.andExpect(model().attribute("student", student))
					.andExpect(model().attribute("viewText", "Add Student"));
	}

	// test for POST add page when adding a new student
	@Test
	public void saveStudentTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/saveStudent")
													.content(asJsonString(Student.builder()
																					.firstName("Vidhu")
																					.lastName("Sharma")
																					.semester("3rd")
																					.year(2021)
																					.score(89)
																					.build()))
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}

	// test for GET add page when updating an existing student
	@Test
	public void updateStudentGetTest() throws Exception {
		Mockito.when(studentService.getStudentById(1))
				.thenReturn(Student.builder()
									.id(1)
									.firstName("Vidhu")
									.lastName("Sharma")
									.semester("3rd")
									.year(2021)
									.score(89)
									.build());

		StudentService studentServiceFromContext = context.getBean(StudentService.class);
		Student student = studentServiceFromContext.getStudentById(1);

		this.mockMvc.perform(get("/updateStudent/{id}", student.getId()))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("add"))
					.andExpect(model().attribute("student", student))
					.andExpect(model().attribute("viewText", "Update Student"));
	}

	// test for GET delete page when asking for confirmation to delete existing
	// student
	@Test
	public void deleteStudentGetTest() throws Exception {
		Mockito.when(studentService.getStudentById(1))
				.thenReturn(Student.builder()
									.id(1)
									.firstName("Vidhu")
									.lastName("Sharma")
									.semester("3rd")
									.year(2021)
									.score(89)
									.build());

		StudentService studentServiceFromContext = context.getBean(StudentService.class);
		Student student = studentServiceFromContext.getStudentById(1);

		this.mockMvc.perform(get("/deleteStudentConfirm/{id}", student.getId()))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("delete"));
	}

	// test for POST delete page when existing student is deleted
	@Test
	public void deleteStudentTest() throws Exception {
		Mockito.when(studentService.getStudentById(1))
				.thenReturn(Student.builder()
									.id(1)
									.firstName("Vidhu")
									.lastName("Sharma")
									.semester("3rd")
									.year(2021)
									.score(89)
									.build());

		StudentService studentServiceFromContext = context.getBean(StudentService.class);
		Student student = studentServiceFromContext.getStudentById(1);

		this.mockMvc.perform(get("/deleteStudent/{id}", student.getId()))
					.andDo(print())
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/studentlist"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
