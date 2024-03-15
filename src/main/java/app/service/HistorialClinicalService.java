package app.service;

import java.util.List;

import app.dto.HistoryClinicalDto;

public interface HistorialClinicalService {
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
	public List<HistoryClinicalDto> consultHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
}
