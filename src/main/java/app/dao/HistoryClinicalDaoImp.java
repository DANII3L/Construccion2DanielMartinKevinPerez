package app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.config.MYSQLConnection;
import app.dto.HistoryClinicalDto;

public class HistoryClinicalDaoImp implements HistoryClinicalDao {
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public void createHistory(HistoryClinicalDto historyClinicalDto) throws Exception {
		String query = "INSERT INTO HISTORIA(FECHA,MASCOTA,MEDICO,MOTIVO,SINTOMATOLOGIA,DIAGNOSIS,PROCEDIMIENTO,MEDICAMENTO,ORDEN,VACUNACION,ALERGIA,DETALLES_PROCEDIMIENTO,MEDICATIONDOSAGE,ORDERCANCELATION)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		
		preparedStatement.setLong(i++, historyClinicalDto.getDate().getTime());
		preparedStatement.setInt(i++, historyClinicalDto.getPetId());
		preparedStatement.setLong(i++, historyClinicalDto.getVetId());
		preparedStatement.setString(i++, historyClinicalDto.getReasonConsult());
		preparedStatement.setString(i++, historyClinicalDto.getSymptomatology());
		preparedStatement.setString(i++, historyClinicalDto.getDiagnosis());
		preparedStatement.setString(i++, historyClinicalDto.getProcedure());
		preparedStatement.setString(i++, historyClinicalDto.getMedicament());
		preparedStatement.setInt(i++, historyClinicalDto.getOrderID());
		preparedStatement.setString(i++, historyClinicalDto.getVaccinationHistory());
		preparedStatement.setString(i++, historyClinicalDto.getDrugAllergy());
		preparedStatement.setString(i++, historyClinicalDto.getDetailProcedure());
		preparedStatement.setString(i++, historyClinicalDto.getMedicationDosage());
		preparedStatement.setBoolean(i++, historyClinicalDto.getOrderCancellation());
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public void cancelOrder(HistoryClinicalDto historyClinicalDto) throws Exception {
		String query = "UPDATE HISTORIA SET ORDERCANCELATION = ? WHERE ORDEN = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;

		preparedStatement.setBoolean(i++, true);
		preparedStatement.setInt(i++, historyClinicalDto.getOrderID());
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public List<HistoryClinicalDto> findHistoryByOrden(HistoryClinicalDto historyClinicalDto) throws Exception {
		String query = "SELECT FECHA,MASCOTA,MEDICO,MOTIVO,SINTOMATOLOGIA,DIAGNOSIS,PROCEDIMIENTO,MEDICAMENTO,ORDEN,VACUNACION,ALERGIA,DETALLES_PROCEDIMIENTO,MEDICATIONDOSAGE,ORDERCANCELATION"
				+ " FROM HISTORIA WHERE ORDEN = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setInt(i++, historyClinicalDto.getOrderID());
		
		List<HistoryClinicalDto> historyList = new ArrayList<>();
		ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        HistoryClinicalDto historyDto = new HistoryClinicalDto(
	                resultSet.getLong("MEDICO"),
	                resultSet.getString("MOTIVO"),
	                resultSet.getString("SINTOMATOLOGIA"),
	                resultSet.getString("DIAGNOSIS"),
	                resultSet.getString("PROCEDIMIENTO"),
	                resultSet.getString("MEDICAMENTO"),
	                resultSet.getString("MEDICATIONDOSAGE"),
	                resultSet.getInt("ORDEN"),
	                resultSet.getString("VACUNACION"),
	                resultSet.getString("ALERGIA"),
	                resultSet.getString("DETALLES_PROCEDIMIENTO"),
	                resultSet.getBoolean("ORDERCANCELATION"),
	                new Date(resultSet.getLong("FECHA")),
	                resultSet.getInt("MASCOTA"));
	        historyList.add(historyDto);
	    }
	    
	    resultSet.close();
		preparedStatement.close();
		
		return historyList;
	}

}
