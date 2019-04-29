package nyla.solutions.net.postit;

import java.io.File;
import java.io.Serializable;

/**
 * 
 * <pre>
 * Package provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Package implements Postable, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4031804007082067059L;

	public Package()
	{
		super();
	}// --------------------------------------------

	/**
	 * @return Returns the bytes.
	 */
	public final byte[] getBytes()
	{
		return bytes;
	}// --------------------------------------------

	/**
	 * @param bytes
	 *            The bytes to set.
	 */
	public final void setBytes(byte[] bytes)
	{

		this.bytes = bytes;
	}// --------------------------------------------

	/**
	 * @return Returns the name.
	 */
	public final String getName()
	{
		return name;
	}// --------------------------------------------

	/**
	 * @param name
	 *            The name to set.
	 */
	public final void setName(String name)
	{
		if (name == null)
			name = "";

		this.name = name;
	}// --------------------------------------------

	/**
	 * @return Returns the text.
	 */
	public final String getText()
	{
		return text;
	}// --------------------------------------------

	/**
	 * @param text
	 *            The text to set.
	 */
	public final void setText(String text)
	{
		if (text == null)
			text = "";

		this.text = text;
	}// --------------------------------------------

	/**
	 * 
	 * @return newInstance("");
	 */
	public static Package newInstance()
	{
		return newInstance("");
	}// --------------------------------------------

	public static Package newInstance(String aText)
	{
		Package p = new Package();
		p.setText(aText);
		return p;
	}// --------------------------------------------

	/**
	 * @return Returns the attachment.
	 */
	public final File getAttachment()
	{
		return attachment;
	}// --------------------------------------------

	/**
	 * @param attachment
	 *            The attachment to set.
	 */
	public final void setAttachment(File attachment)
	{
		this.attachment = attachment;
	}// ------------------------------------------------

	/**
	 * @return the to
	 */
	public String getTo()
	{
		return to;
	}//------------------------------------------------

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to)
	{
		this.to = to;
	}//------------------------------------------------

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from)
	{
		this.from = from;
	}

	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	private File attachment = null;
	private String name = "";
	private String text = null;
	private byte[] bytes = null;
	private String to;
	private String from;
	private String subject;
}
