package pixel.academy.crud_app.dao;

import pixel.academy.crud_app.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

}
