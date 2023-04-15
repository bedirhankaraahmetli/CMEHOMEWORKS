public class Participant {
	public int ID;
	public String Name;
	public String BirthDate;
	public String Number;
	public String Adress;
	public Boolean isPlayed = false;
	
	Participant(int inputID, String inputName, String inputBirthDate, String inputNumber, String inputAdress, Boolean inputisPlayed){
		
		ID = inputID;
		Name = inputName;
		BirthDate = inputBirthDate;
		Number = inputNumber;
		Adress = inputAdress;
		isPlayed = inputisPlayed;
	}

	public Boolean getisPlayed() {
		return isPlayed;
	}

	public void setisPlayed(Boolean isPlayed) {
		this.isPlayed = isPlayed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}
	
}