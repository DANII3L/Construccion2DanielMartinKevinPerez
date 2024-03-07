package app.dto;

import app.models.Pet;

public class PetDto {
	private String id;
	private String name;
	private long ownerId;
	private int age;
	private String species;
	private String race;
	private String characteristics;
	private String weight;
	
	public PetDto(Pet pet) {
		this.id = pet.getId();
		this.name = pet.getName();
		this.ownerId = pet.getOwnerId();
		this.age = pet.getAge();
		this.species = pet.getSpecies();
		this.race = pet.getRace();
		this.characteristics = pet.getCharacteristics();
		this.weight = pet.getWeight();
	}
	
	public PetDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
}
