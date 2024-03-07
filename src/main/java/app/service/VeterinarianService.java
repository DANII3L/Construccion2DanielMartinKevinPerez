package app.service;

import app.dto.HistoryClinicalDto;
import app.dto.PersonDto;
import app.dto.PetDto;

public interface VeterinarianService {
	public void createUser(PersonDto personDto) throws Exception;
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
	public void createPet(PetDto petDto) throws Exception;
}
