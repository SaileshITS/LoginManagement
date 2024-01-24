package code.with.sailesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.with.sailesh.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	User findByEmail(String email);
}
