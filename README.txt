It's only requirements are to make sure your project has the following features/implementations
- Must be a spring MVC application
	There is a model class for students. There are views for CRUD. The controller has method for creating, reading, updating and deleting students.

- A page with a form where users have to input information
	add.html

- Website must have atleast 5 pages [5]
	add.html, contact.html, delete.html, studentList.html, welcome.html

- must have atleast 3 fields for the user to fill out [5]
	In add.html form, there are 6 fields for the user to fill out: studentNo, firstName, lastName, score, semester, year.

- form must be validated on the server side (for every field) [5]
	Annotations in Model class Student are used for validation. In add.html form, thymeleaf is used to show validation messages.

- contents from the form should be persisted if it passes validation (saved into a database) [8]
	The records for student are saved in the database on AWS. The settings for the database can be found in application.properties file.

- A page that users can go to that lists the items created from the form in requirement 1 using Templates/Thymeleaf [8]
	studentList.html

- must take an optional get param to filter the list by an attribute  [4]
	In studentList.html, a form is used. When the user enters a value in the textfield and submit the form by pressing the button, the 
	table shows only the students which have the textfield value in one of their attribute values.

- There must be an API that returns the number of page hits since the server was online
	In StudentController.java, method getPageHits() mapped by "/currentCount"

- This api should be called asynchronously every 3 seconds and the results displayed on every page [8]
	Results are displayed in the footer of each page using the ajax call.

- There must be at-least 1 dependency injected into two different locations in the project [7]
	In StudentController.java, there are two dependency injections:	private StudentService studentService; private PageHitCount pageHitCount;
	In StudentServiceImpl.java, there is one dependency injection: private StudentRepository studentRepository;

- use of lombok in data classes [3]
	Student.java

- Aesthetically pleasing website (e.x using css or frameworks) (10 points)
	Bootstrap and CSS is used.

Additionally
- Classes should have unit tests [10]
	StudentControllerTest.java
