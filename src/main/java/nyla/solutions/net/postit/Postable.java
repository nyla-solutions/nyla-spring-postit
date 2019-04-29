package nyla.solutions.net.postit;

import java.io.File;

public interface Postable
{
	
	
   /**
    * 
    * @return the file attachment
    */
   public File getAttachment();
   
   /**
    * 
    * @param aName the name to set
    */
   public void setName(String aName);
   
   /**
    * 
    * @return the name
    */
   public String getName();
   
   /**
    * 
    * @return test content
    */
   public String getText();
   
   /**
    * 
    * @return the byte content
    */
   public byte[] getBytes();
}