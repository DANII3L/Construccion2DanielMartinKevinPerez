package app.service;

import app.dto.FactureDto;
import app.dto.PersonDto;
import app.dto.PetDto;

public interface VeterinarianService {
	public void createUser(PersonDto personDto, String rol) throws Exception;
	public void createPet(PetDto petDto) throws Exception;
	public void createFacture(FactureDto factureDto) throws Exception;
}
