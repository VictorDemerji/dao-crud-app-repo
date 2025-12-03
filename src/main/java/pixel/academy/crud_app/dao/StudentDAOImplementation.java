package pixel.academy.crud_app.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pixel.academy.crud_app.entity.Student;

@Repository
public class StudentDAOImplementation implements StudentDAO {

    //Fiel for EntityManager (will be used for injection with data baze)
    private EntityManager entityManager;

    //Inject EntityManager by constructor (practice recomand for testability and modularity)
    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Implementing the save method to save a Student object to the database
    @Override
    @Transactional
    public void save(Student theStudent) {
    entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }
}