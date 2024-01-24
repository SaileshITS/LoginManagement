package code.with.sailesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.with.sailesh.entity.MPinDetails;
import code.with.sailesh.entity.User;

@Repository
public interface MPinDetailsRepo extends JpaRepository<MPinDetails, Integer>
{
	MPinDetails findByUserId(User userId);
}
