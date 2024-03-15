package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.config.MYSQLConnection;
import app.dto.FactureDto;

public class FactureDaoImp implements FactureDao {
	public Connection connection = MYSQLConnection.getConnection();
	
	@Override
	public void createFacture(FactureDto factureDto) throws Exception {
		String query = "INSERT INTO FACTURA(MASCOTA,PROPIETARIO,PRODUCTO,VALOR,CANTIDAD,FECHA)"
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;

		preparedStatement.setInt(i++, factureDto.getPetId());
		preparedStatement.setLong(i++, factureDto.getOwnerId());
		preparedStatement.setString(i++, factureDto.getMedicineName());
		preparedStatement.setDouble(i++, factureDto.getCost());
		preparedStatement.setInt(i++, factureDto.getAmount());
		preparedStatement.setDate(i++, factureDto.getDate());
		preparedStatement.execute();
		preparedStatement.close();
	}
}
