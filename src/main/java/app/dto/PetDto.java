package app.dto;

public class PetDto {
	private int id;
	private String name;
	private long ownerId;
	private int age;
	private String species;
	private String race;
	private String characteristics;
	private Double weight;

	public PetDto(int id, String name, long ownerId, int age, String species, String race, String characteristics,
			Double weight) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.age = age;
		this.species = species;
		this.race = race;
		this.characteristics = characteristics;
		this.weight = weight;
	}

	public PetDto() {
	}
	
	public PetDto(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
