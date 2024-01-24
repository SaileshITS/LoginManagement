package code.with.sailesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.with.sailesh.entity.LoginHistory;

@Repository
public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Integer>
{
	LoginHistory findByDeviceId(String deviceId);
}
