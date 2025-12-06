package pixel.academy.crud_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pixel.academy.crud_app.dao.StudentDAO;
import pixel.academy.crud_app.entity.Student;

import java.util.List;

@SpringBootApplication
public class CrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

        return runner -> {
            //createStudent(studentDAO);
            //createMultileStudents(studentDAO);
            //readStudent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            deleteAllStudent(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDao) {

        //create a Student object
        System.out.println("Creating new student object ...");
        Student newStudent = new Student("John", "Doe", "john@pixelacademy.md");

        //save Student object in data baze using DAO
        System.out.println("Saving de student ...");
        studentDao.save(newStudent);

        // disply saved Student ID
        System.out.println("Saved student. Generated id: " + newStudent.getId());
    }

    private void createMultileStudents(StudentDAO studentDAO) {
        //create more students
        System.out.println("Creating 3 student objects ...");
        Student newStudent1 = new Student("Andrei", "Munteanu", "munteanu@pixelacedemy.md");
        Student newStudent2 = new Student("Iulian","Vataman", "iulic@pixelacedemy.md");
        Student newStudent3 = new Student("Maria", "Mirabela","mira@pixelacademy.md");

        //save student objects in data baze
        System.out.println("Saving the students ...");
        studentDAO.save(newStudent1);
        studentDAO.save(newStudent2);
        studentDAO.save(newStudent3);
    }

    private void readStudent(StudentDAO studentDAO) {
        //create a student object
        System.out.println("Creating new student object ...");
        Student newStudent = new Student("Mircea", "Popescu", "mirceap@pixel.academy");

        // save student in data baze
        System.out.println("Saving the student ...");
        studentDAO.save(newStudent);

        //display saved student ID
        int theId = newStudent.getId();
        System.out.println("Saved student. Generate id:" + theId);

        //recover student based on id
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDAO.findById(theId);

        // display student detail
        System.out.println("Found the student: " + myStudent);
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //obtain list of students
        List<Student> theStudents = studentDAO.findAll();

        //display list of students
        for (Student newStudent : theStudents) {
            System.out.println(newStudent);
        }
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        //return list of students
        List<Student> theStudent = studentDAO.findByLastName("Popescu");

        //display list of students
        for(Student newStudent : theStudent) {
            System.out.println(newStudent);
        }
    }

    private void updateStudent(StudentDAO studentDAO) {
        //find student in database using ID (primary key)
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student newStudent = studentDAO.findById(studentId);

        //modify student first name in ,,Ion"
        System.out.println("Updating student...");
        newStudent.setFirstName("Ion");

        //save modified in database
        studentDAO.update(newStudent);

        //display student details updated
        System.out.println("Update student: " + newStudent);
    }

    private void deleteStudent(StudentDAO studentDAO) {

        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void deleteAllStudent(StudentDAO studentDAO) {
        System.out.println("Deleting all students.");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }
}