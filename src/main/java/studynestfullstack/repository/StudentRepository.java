package studynestfullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import studynestfullstack.entity.Student;
import studynestfullstack.entity.User;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByUser(User user);

    List<Student> findByUserAndNameContainingIgnoreCase(
            User user,
            String name
    );

    long countByUser(User user);

    List<Student> findByUserOrderByNameAsc(User user);

    List<Student> findByUserOrderByNameDesc(User user);

    List<Student> findByUserOrderByIdDesc(User user);

}