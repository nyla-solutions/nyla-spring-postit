package nyla.solutions.net.postit.exception;

public class PostItException extends Exception
{

   public PostItException()
   {
      super();
      // TODO Auto-generated constructor stub
   }

   public PostItException(String arg0)
   {
      super(arg0);
   }

   public PostItException(Throwable arg0)
   {
      super(arg0);
   }// --------------------------------------------

   public PostItException(String arg0, Throwable arg1)
   {
      super(arg0, arg1);
   }// --------------------------------------------
   
   static final long serialVersionUID = PostItException.class.getName()
   .hashCode();

}
