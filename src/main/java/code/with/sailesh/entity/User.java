package code.with.sailesh.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String department;
	private String email;
	private String mobile;
	private String password;
	private Boolean isActive;
	
	 @OneToMany(mappedBy="user")
	    private Set<LoginHistory> loginHistories;
	 @OneToMany(mappedBy="user")
	    private Set<PasswordHistory> passwordHistories;
}
