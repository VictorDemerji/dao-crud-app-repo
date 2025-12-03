package pixel.academy.crud_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pixel.academy.crud_app.dao.StudentDAO;
import pixel.academy.crud_app.entity.Student;

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
            readStudent(studentDAO);
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

}
