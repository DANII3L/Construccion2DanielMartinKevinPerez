package app.dao;

import app.dto.FactureDto;

public interface FactureDao {
	public void createFacture(FactureDto factureDto) throws Exception;
}
