package fit.se.www_lab_w6.repositories;

import fit.se.www_lab_w6.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByEmailAndPassword(String email, String password);
}
