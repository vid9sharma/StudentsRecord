package ca.lambton.c0772709_Project;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private PageHitCount pageHitCount;

	// home page
	@GetMapping("/")
	public String homePage() {
		
		pageHitCount.incrementCounter();
		return "welcome";
	}

	// contact page
	@GetMapping("/contact")
	public String contactPage() {
		
		pageHitCount.incrementCounter();
		return "contact";
	}

	// student list page
	@GetMapping("/studentlist")
	public String listOfStudents(Model model, String searchFilter) {
		
		List<Student> listOfStudents = new ArrayList<Student>();

		if (searchFilter == null || searchFilter.equals("")) {
			listOfStudents = studentService.getAllStudents();
			
		} else {
			model.addAttribute("filterValue", searchFilter);
			listOfStudents = studentService.getAllStudentsAfterFilter(searchFilter);
		}

		model.addAttribute("studentList", listOfStudents);
		
		pageHitCount.incrementCounter();
		return "studentList";
	}

	// to add student GET
	@GetMapping("/addStudent")
	public String addStudentGet(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("viewText", "Add Student");

		pageHitCount.incrementCounter();
		return "add";
	}

	// to add or update student POST
	@PostMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("viewText", "Add Student");

			pageHitCount.incrementCounter();
			return "add";
		}

		studentService.saveStudent(student);
		pageHitCount.incrementCounter();
		return "redirect:/studentlist";
	}

	// to update student GET
	@GetMapping("/updateStudent/{id}")
	public String updateStudentGet(@PathVariable(value = "id") int id, Model model) {
		
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		model.addAttribute("viewText", "Update Student");

		pageHitCount.incrementCounter();
		return "add";
	}

	// confirmation for student to delete GET
	@GetMapping("/deleteStudentConfirm/{id}")
	public String deleteStudentGet(@PathVariable(value = "id") int id, Model model) {
		
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);

		pageHitCount.incrementCounter();
		return "delete";
	}

	// to delete student
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable(value = "id") int id) {
		
		studentService.deleteStudentByid(id);

		pageHitCount.incrementCounter();
		return "redirect:/studentlist";
	}

	// to get current hits on the website
	@GetMapping("/currentCount")
	@ResponseBody
	public Integer getPageHits() {
		
		return pageHitCount.getCounter();
	}
}
