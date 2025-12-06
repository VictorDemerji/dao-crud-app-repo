package pixel.academy.crud_app.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pixel.academy.crud_app.entity.Student;

import java.util.List;

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



    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc ", Student.class);
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName desc ", Student.class);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {

        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        //set parameters for query
        theQuery.setParameter("theData", theLastName);

        //return result for query
        return theQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        //take over student from database
        Student theStudent = entityManager.find(Student.class, id);

        //delete student
        entityManager.remove(theStudent);

    }

    @Transactional
    @Override
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return 0;
    }
}