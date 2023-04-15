public class Question {
	private int ID;
	private String Category;
	private String Text;
	private String ChoiceA;
	private String ChoiceB;
	private String ChoiceC;
	private String ChoiceD;
	private String CorrectAnswer;
	private int Difficulty;
	private boolean IsAsked;
	private String [] Words;
	private String [] KeyWords;
	
	Question(int inputID, String inputCategory, String inputText, String inputChoiceA, String inputChoiceB, 
			String inputChoiceC, String inputChoiceD, String inputCorrectAnswer, 
			int inputDifficulty, boolean inputIsAsked, String[] inputWords, String[] inputKeyWords){
		
		ID = inputID;
		Category = inputCategory;
		Text = inputText;
		ChoiceA = inputChoiceA;
		ChoiceB = inputChoiceB;
		ChoiceC = inputChoiceC;
		ChoiceD = inputChoiceD;
		CorrectAnswer = inputCorrectAnswer;
		Difficulty = inputDifficulty;
		IsAsked = inputIsAsked;
		Words = inputWords;
		KeyWords = inputKeyWords;
		
	}
	
	public int getID() {
		return ID;
	}
	
	public String getCategory() {
		return Category;
	}
	
	public String getText() {
		return Text;
	}
	
	public String getChoiceA() {
		return ChoiceA;
	}
	
	public String getChoiceB() {
		return ChoiceB;
	}
	
	public String getChoiceC() {
		return ChoiceC;
	}
	
	public String getChoiceD() {
		return ChoiceD;
	}
	
	public String getCorrectAnswer() {
		return CorrectAnswer;
	}
	
	public int getDifficulty() {
		return Difficulty;
	}
	
	public boolean getIsAsked() {
		return IsAsked;
	}
	
	public String[] getWords() {
		return Words;
	}
	
	public String[] getKeyWords() {
		return KeyWords;
	}
	
	public void setID(int inputID) {
		ID = inputID;
	}

	public void setCategory(String inputCategory) {
		Category = inputCategory;
	}
	
	public void setText(String inputText) {
		Text = inputText;
	}
	
	public void setChoiceA(String inputChoiceA) {
		ChoiceA = inputChoiceA;
	}
	
	public void setChoiceB(String inputChoiceB) {
		ChoiceB = inputChoiceB;
	}
	
	public void setChoiceC(String inputChoiceC) {
		ChoiceC = inputChoiceC;
	}
	
	public void setChoiceD(String inputChoiceD) {
		ChoiceD = inputChoiceD;
	}
	
	public void setCorrectAnswer(String inputCorrectAnswer) {
		CorrectAnswer = inputCorrectAnswer;
	}
	
	public void setDifficulty(int inputDifficulty) {
		Difficulty = inputDifficulty;
	}
	
	public void getIsAsked(boolean inputIsAsked) {
		IsAsked = inputIsAsked;
	}
	
	public void setWords(String[] inputWords) {
		Words = inputWords;
	}
	
	public void setKeyWords(String[] inputKeyWords) {
		KeyWords = inputKeyWords;
	}
	
}