package nyla.solutions.net.postit;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.net.postit.data.Recipient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PostItApp.class})
public class PostItMgrIntTest
{
	@Autowired
	ApplicationContext ctx;

	@Test
	public void testFindRecipients()
	{
		//try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		//{
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			
			Recipient recipient = new Recipient();
			recipient.setEmail("email");
			recipient.setRecipientId(recipient.getEmail());
			recipient.setId(recipient.getRecipientId());
			
			
			Assert.assertNotNull(recipient.getRecipientId());
			Assert.assertEquals("email", recipient.getRecipientId());
			
			mgr.saveRecipient(recipient);
			
			
			Iterable<UserProfile> recipients = mgr.findRecipients();
			
			Assert.assertNotNull(recipients);
			Assert.assertTrue(recipients.iterator().hasNext());
			
		//}
		
		
	}//------------------------------------------------

	@Test
	public void testSaveRecipient()
	{
		//try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		//{
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			UserProfile recipient = new UserProfile();
			try
			{
			
				mgr.saveRecipient(recipient);
				Assert.fail();
			}
			catch(RuntimeException e)
			{}
			
			recipient.setId("id");
			
			mgr.saveRecipient(recipient);
			
			
			Iterable<UserProfile> recipients = mgr.findRecipients();
			
			Assert.assertNotNull(recipients);
			Assert.assertTrue(recipients.iterator().hasNext());
			
		//}
	}//------------------------------------------------
	@Test
	//@Ignore
	public void testSendMail()
	{
		Environment env  = new StandardEnvironment();
		
		//try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		//{
			
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			Recipient recipient = new Recipient();
			
			Package pack = new Package();
			pack.setSubject("Junit Testing");
			pack.setText(env.getProperty("mail.content","<b>Hello World</b>"));
			try
			{
				mgr.sendIt(pack);
				Assert.fail("required to");
			}
			catch(RuntimeException e)
			{}
			
			pack.setTo(env.getProperty("mail.to"));
			
			mgr.sendIt(pack);

			
			
		//}
	} //------------------------------------------------

	
	@Test
	//@Ignore
	public void testSendMailUnknownEmails()
	{

			
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
		
			String emails = "info@TheRevelationSquad.com \n"+
					", \nKATHARINE@ATLANTISNWTRAINING.COM\n"+
					",:; info@TheRevelationSquad.com\n"+
					":;info@TheRevelationSquad.com";
			
			mgr.removeRecipientsByEmails(emails);
			
			Assert.assertNull(mgr.findRecipientsByEmails(emails));
			
			
			Package pack = new Package();
			pack.setSubject("Junit Testing");
			pack.setText("Hello world");
			
			pack.setTo(emails);
			
			mgr.sendIt(pack);

			
			Iterable<UserProfile> users = mgr.findRecipientsByEmails(emails);
			
			Assert.assertNotNull(users);
			
			Assert.assertEquals(1, users.spliterator().getExactSizeIfKnown());
			
			
		//}
	} //------------------------------------------------
}
