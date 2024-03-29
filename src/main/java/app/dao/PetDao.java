package app.dao;

import app.dto.PetDto;

public interface PetDao {
	public PetDto findById(PetDto petDto) throws Exception;
	public boolean findPetExist(PetDto petDto) throws Exception;
	public void createPet(PetDto petDto) throws Exception;
}
