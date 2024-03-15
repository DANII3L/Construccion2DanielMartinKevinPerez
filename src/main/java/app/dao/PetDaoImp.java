package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dto.PetDto;

public class PetDaoImp implements PetDao {
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public PetDto findById(PetDto petDto) throws Exception {
		String query = "SELECT ID,NOMBRE,PROPIETARIO,EDAD,ESPECIE,RAZA,CARACTERISTICAS,PESO FROM MASCOTA WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, petDto.getId());
		
		ResultSet resulSet = preparedStatement.executeQuery();
		if (resulSet.next()) {
			PetDto petDtoReturn = new PetDto(resulSet.getInt("ID"),
					resulSet.getString("NOMBRE"),
					resulSet.getLong("PROPIETARIO"),
					resulSet.getInt("EDAD"),
					resulSet.getString("ESPECIE"),
					resulSet.getString("RAZA"),
					resulSet.getString("CARACTERISTICAS"),
					resulSet.getDouble("PESO"));
			resulSet.close();
			preparedStatement.close();
			return petDtoReturn;
		}
		resulSet.close();
		preparedStatement.close();
		return null;
	}

	@Override
	public boolean findPetExist(PetDto petDto) throws Exception {
		String query = "SELECT 1 FROM MASCOTA WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, petDto.getId());
		ResultSet resulSet = preparedStatement.executeQuery();
		boolean petExist = resulSet.next();
		resulSet.close();
		preparedStatement.close();
		return petExist;
	}
	
	@Override
	public void createPet(PetDto petDto) throws Exception {
		String query = "INSERT INTO MASCOTA(NOMBRE,PROPIETARIO,EDAD,ESPECIE,RAZA,CARACTERISTICAS,PESO) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setString(i++, petDto.getName());
		preparedStatement.setLong(i++, petDto.getOwnerId());
		preparedStatement.setInt(i++, petDto.getAge());
		preparedStatement.setString(i++, petDto.getSpecies());
		preparedStatement.setString(i++, petDto.getRace());
		preparedStatement.setString(i++, petDto.getCharacteristics());
		preparedStatement.setDouble(i++, petDto.getWeight());
		preparedStatement.execute();
		preparedStatement.close();
	}
}
