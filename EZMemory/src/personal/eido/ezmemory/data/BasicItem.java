package personal.eido.ezmemory.data;

public class BasicItem {
	static public final int RANK_LENGTH = 11;
	static public final int RANK_MAX = 10;
	static public final int RANK_MIN = 0;
	static public final int RANK_DEFAULT = 5;
	
	private String keyWord;
	private String shortDescription;
	private String longDescription;
	private int rank;
	
	public BasicItem(String keyWord, String shortDesc){
		this(keyWord, shortDesc, BasicItem.RANK_DEFAULT);
	}
	
	public BasicItem(String keyWord, String shortDesc, int rank){
		this(keyWord, shortDesc, "", rank);
	}
	
	public BasicItem(String keyWord, String shortDesc, String longDesc, int rank){
		this.keyWord = keyWord;
		this.shortDescription = shortDesc;
		this.longDescription = longDesc;
		this.setRank(rank);
	}
	
	
	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}
	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	/**
	 * @return the shortDescription
	 */
	public String getshortDescription() {
		return shortDescription;
	}
	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setshortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}
	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		if(rank > BasicItem.RANK_MAX)
			this.rank = BasicItem.RANK_MAX;
		else if (rank < BasicItem.RANK_MIN)
			this.rank = BasicItem.RANK_MIN;
		else
			this.rank = rank;
	}
	
	public void increaseRank() {
		setRank(getRank()+1);
	}
	
	public void decreaseRank() {
		setRank(getRank()-1);
	}
	
}
