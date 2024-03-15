package app.dao;

import java.util.List;

import app.dto.HistoryClinicalDto;

public interface HistoryClinicalDao {
	public void createHistory(HistoryClinicalDto historyClinicalDto) throws Exception;
	public void cancelOrder(HistoryClinicalDto historyClinicalDto) throws Exception;
	public List<HistoryClinicalDto> findHistoryByOrden(HistoryClinicalDto historyClinicalDto) throws Exception;
}
