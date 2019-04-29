package nyla.solutions.net.postit.data;

import org.springframework.data.annotation.Id;

import nyla.solutions.core.security.user.data.UserProfile;

/**
 * <pre>
 * Recipient is a value object representation of the Recipient table
 * and associated entities.
 * 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Recipient extends UserProfile
{

   /**
    * Constructor for Recipient initializes internal 
    * data settings.
    */
   public Recipient()
   {
      super();
   }// --------------------------------------------

   

   /**
    * Constructor for Recipient initializes internal 
    * data settings.
    * @param email
    * @param loginID
    * @param firstName
    * @param lastName
    */
   public Recipient(String email, String loginID, String firstName,
   String lastName)
   {
      super(email, loginID, firstName, lastName);
   }
   
   
	public String getRecipientId()
	{
		if(this.recipientId == null)
			this.recipientId = this.getId();
		
	   return recipientId;
	}

	public void setRecipientId(String recipientId)
	{
		this.recipientId = recipientId;
	}
   
   @Id
   private String recipientId = null;
   
   static final long serialVersionUID = Recipient.class.getName().hashCode();

}
