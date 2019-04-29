package nyla.solutions.net.postit;

import nyla.solutions.net.postit.exception.PostItException;

/**
 * 
 * <pre>
 * PostItService provides a set of functions to post content
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface PostItService
{
   /**
    * SERVICE_NAME = PostItService.class.getName()
    */
   public static final String SERVICE_NAME = PostItService.class.getName();
   
   public void post(String aTo, Postable aPostable)
   throws PostItException;
   
   
   /**
    * 
    * @param aPostable
    */
   public void post(Postable aPostable)
   throws PostItException;

}
