package code.with.sailesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.with.sailesh.entity.PasswordHistory;

@Repository
public interface PasswordHistoryRepo extends JpaRepository<PasswordHistory, Integer>
{
	
}
