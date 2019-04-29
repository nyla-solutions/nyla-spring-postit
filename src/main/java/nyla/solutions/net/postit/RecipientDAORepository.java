package nyla.solutions.net.postit;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nyla.solutions.core.security.user.data.UserProfile;

@Repository
//@Region("Recipient")
public interface RecipientDAORepository extends CrudRepository<UserProfile, String>
{

	/**
	 * 
	 * @param email the email search
	 * @return list of recipient
	 */
	public List<UserProfile> findByEmailLike(String email);
}
