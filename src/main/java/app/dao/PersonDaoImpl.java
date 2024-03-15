package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dto.PersonDto;

public class PersonDaoImpl implements PersonDao {
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public void createPerson(PersonDto personDto) throws Exception {
		String query = "INSERT INTO PERSONA(CEDULA,NOMBRE,EDAD,USERNAME,ROL,PASSWORD) VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setLong(i++, personDto.getCedula());
		preparedStatement.setString(i++, personDto.getFullName());
		preparedStatement.setInt(i++, personDto.getAge());
		preparedStatement.setString(i++, personDto.getUserName());
		preparedStatement.setString(i++, personDto.getRol());
		preparedStatement.setString(i++, personDto.getPassword());
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public boolean findUserExist(PersonDto personDto) throws Exception {
		String query = "SELECT 1 FROM PERSONA WHERE CEDULA = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, personDto.getCedula());
		ResultSet resulSet = preparedStatement.executeQuery();
		boolean existUser = resulSet.next();
		resulSet.close();
		preparedStatement.close();
		return existUser;
	}

	@Override
	public PersonDto findUserById(PersonDto personDto) throws Exception {
		String query = "SELECT CEDULA,NOMBRE,EDAD,USERNAME,ROL,PASSWORD FROM PERSONA WHERE CEDULA = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, personDto.getCedula());
		ResultSet resulSet = preparedStatement.executeQuery();
		if(resulSet.next()) {
			PersonDto personDtoReturn = new PersonDto(resulSet.getLong("CEDULA"),
					resulSet.getString("NOMBRE"),
					resulSet.getString("ROL"),
					resulSet.getString("USERNAME"),
					resulSet.getString("PASSWORD"),
					resulSet.getInt("EDAD"));
			resulSet.close();
			preparedStatement.close();
			return personDtoReturn;
		}
		resulSet.close();
		preparedStatement.close();
		return null;
	}

	@Override
	public boolean existUserByUserName(PersonDto personDto) throws Exception {
		String query = "SELECT 1 FROM PERSONA WHERE USERNAME = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, personDto.getUserName());
		ResultSet resulSet = preparedStatement.executeQuery();
		boolean existUser = resulSet.next();
		resulSet.close();
		preparedStatement.close();
		return existUser;
	}

	@Override
	public PersonDto findUserByUserName(PersonDto personDto) throws Exception {
		String query = "SELECT CEDULA,NOMBRE,ROL,EDAD,USERNAME,PASSWORD FROM PERSONA WHERE USERNAME = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, personDto.getUserName());
		ResultSet resulSet = preparedStatement.executeQuery();
		if(resulSet.next()) {
			PersonDto personDtoReturn = new PersonDto(resulSet.getLong("CEDULA"),
					resulSet.getString("NOMBRE"),
					resulSet.getString("ROL"),
					resulSet.getString("USERNAME"),
					resulSet.getString("PASSWORD"),
					resulSet.getInt("EDAD"));
			resulSet.close();
			preparedStatement.close();
			return personDtoReturn;
		}
		resulSet.close();
		preparedStatement.close();
		return null;
	}

}
