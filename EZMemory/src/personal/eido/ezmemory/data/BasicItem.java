package personal.eido.ezmemory.data;

public class BasicItem {
	private final int RANK_MAX = 10;
	private final int RANK_MIN = 0;
	private final int RANK_DEFAULT = 5;
	
	private String keyWord;
	private String shortDescription;
	private String longDescription;
	private int rank;
	
	public BasicItem(String keyWord, String shortDesc){
		new BasicItem(keyWord, shortDesc, this.RANK_DEFAULT);
	}
	
	public BasicItem(String keyWord, String shortDesc, int rank){
		new BasicItem(keyWord, shortDesc, "", rank);
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
	public String getMeaning() {
		return shortDescription;
	}
	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setMeaning(String meaning) {
		this.shortDescription = meaning;
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
		if(rank > this.RANK_MAX)
			this.rank = this.RANK_MAX;
		else if (rank < this.RANK_MIN)
			this.rank = this.RANK_MIN;
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
