package action.model.vo;

public class Person {
	private String name;
	private char gender;
	private int nai;
///	private int age;
	
	public Person() {}

	public Person(String name, char gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
//		this.age = age;
		this.nai = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

//	public int getAge() {
//		return age;
//	}
	
	public int getNai() {
		return nai;
	}

//	public void setAge(int age) {
//		this.age = age;
//	}
	
	public void setNai(int nai) {
		this.nai = nai;
	}

	@Override
	public String toString() {
//		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
		return "Person [name=" + name + ", gender=" + gender + ", nai=" + nai + "]";
	}
	
	
}
