package nz.ac.auckland.library.domain;

/**
 * Enum type to represent the genre of a book
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
public enum BookGenre {
	FICTION, NONFICTION, ACTION, ROMANCE, HORROR, HEALTH, TRAVEL, RELIGION, 
	SCIENCE, HISTORY, POETRY, ART, COOKBOOK, ENCYCLOPEDIA, DICTIONARY, 
	JOURNAL, BIOGRAPHY, OTHER;
	
	/**
	 * Return a Book Genre that corresponds to a String value.
	 */
	public static BookGenre fromString(String text) {
		if (text != null) {
			for (BookGenre g : BookGenre.values()) {
				if (text.equalsIgnoreCase(g.toString())) {
					return g;
				}
			}
		}
		return OTHER;
	}
	
}
