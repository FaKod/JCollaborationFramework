package org.jcf.graphicMessage;


public class TextImpl extends GraphicObjectImpl implements Text {
	
	/**
	 * the stored text
	 */
	private String text;

	/**
	 * Version ID
	 */
	private static final long serialVersionUID = 1L;

	public TextImpl(Id id) {
		super(id);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TextImpl with id: " + getId();
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
