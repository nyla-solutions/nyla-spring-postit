package nyla.solutions.net.postit.mail;

import java.io.IOException;
import java.util.Collection;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.email.Email;
import nyla.solutions.email.data.EmailMessage;
import nyla.solutions.net.postit.PostItService;
import nyla.solutions.net.postit.Postable;
import nyla.solutions.net.postit.RecipientDAORepository;
import nyla.solutions.net.postit.exception.PostItException;

@Component
public class MailPostItService implements PostItService
{

   public MailPostItService()
   {
      super();
   }// --------------------------------------------
   /**
    * 
    * Post using email
    * @see nyla.solutions.net.postit.PostItService#post(java.lang.String, nyla.solutions.net.postit.Postable)
    */
   public void post(String aTo, Postable aPostable)
   throws PostItException
   {
      try(Email email = new Email();)
      {
        email.sendMail(aTo, aPostable.getName(), aPostable.getText(), aPostable.getAttachment());
      }
      catch (Exception e)
      {
         throw new PostItException(Debugger.stackTrace(e));
      }
   }// --------------------------------------------
   /**
    * 
    * @param aPostable
    */
   public void post(Postable aPostable)
   throws PostItException
   {
     
      try
      {
    	  Iterable<UserProfile> recipients = recipientDAORepository.findAll();
    	  
         for (UserProfile recipient : recipients)
		{
        	 this.post(recipient.getEmail(), aPostable);
		}
        
      }
      catch(NoDataFoundException aNoDataFoundException)
      {
         Debugger.printWarn(this,aNoDataFoundException);
      }      
   }// --------------------------------------------
   public void cleanup()
   throws IOException, MessagingException
   {
	   int count= 100;
	   int startIndex =1;
	   String pattern = ".*Undeliver.*";
	   
	   try(Email email = new Email();)
	   {
			   
		   
		  Collection<EmailMessage> collection = email.readMatches(count, startIndex, pattern);
		  
		  if(collection == null)
			  return;
		  
		  for (EmailMessage emailMessage : collection)
		  {
			  System.out.println("DELETING:"+emailMessage);
				
			  //recipientDAORepository.delete(email);
		  }
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
   }//------------------------------------------------
   @Autowired
   private RecipientDAORepository recipientDAORepository;
   
}
