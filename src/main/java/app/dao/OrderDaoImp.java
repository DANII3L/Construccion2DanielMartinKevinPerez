package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dto.OrderDto;

public class OrderDaoImp implements OrderDao{
	public Connection connection = MYSQLConnection.getConnection();
	
	@Override
	public OrderDto createOrder(OrderDto orderDto) throws Exception {
		String query = "INSERT INTO ORDEN(MASCOTA,PROPIETARIO,MEDICO,MEDICAMENTO,FECHA)"
				+ " VALUES (?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		
		preparedStatement.setInt(i++, orderDto.getPetId());
		preparedStatement.setLong(i++, orderDto.getOwnerID());
		preparedStatement.setLong(i++, orderDto.getVetId());
		preparedStatement.setString(i++, orderDto.getMedicineName());
		preparedStatement.setDate(i++, orderDto.getDateRegister());
		preparedStatement.execute();
		preparedStatement.close();
		
		query = "SELECT ID,MASCOTA,PROPIETARIO,MEDICO,MEDICAMENTO,FECHA "
				+ "FROM ORDEN WHERE MASCOTA = ? AND FECHA = ?";
		preparedStatement = connection.prepareStatement(query);
		i = 1;
		preparedStatement.setInt(i++, orderDto.getPetId());
		preparedStatement.setDate(i++, orderDto.getDateRegister());
		
		ResultSet resulSet = preparedStatement.executeQuery();
		if (resulSet.next()) {
			OrderDto orderDtoReturn = new OrderDto(resulSet.getInt("ID"),
					resulSet.getInt("MASCOTA"),
					resulSet.getLong("PROPIETARIO"),
					resulSet.getLong("MEDICO"),
					resulSet.getString("MEDICAMENTO"),
					resulSet.getDate("FECHA"));
			resulSet.close();
			preparedStatement.close();
			return orderDtoReturn;
		}
		resulSet.close();
		preparedStatement.close();
		return null;
	}
	
	@Override
	public boolean findOrderExist(OrderDto orderDto) throws Exception {
		String query = "SELECT 1 FROM ORDEN WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, orderDto.getId());
		ResultSet resulSet = preparedStatement.executeQuery();
		boolean orderExiste = resulSet.next();
		resulSet.close();
		preparedStatement.close();
		return orderExiste;
	}
	
	public OrderDto searchOrder(OrderDto orderDto) throws Exception {
		String query = "SELECT ID,MASCOTA,PROPIETARIO,MEDICO,MEDICAMENTO,FECHA "
				+ "FROM ORDEN WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, orderDto.getId());
		
		ResultSet resulSet = preparedStatement.executeQuery();
		if (resulSet.next()) {
			OrderDto orderDtoReturn = new OrderDto(resulSet.getInt("ID"),
					resulSet.getInt("MASCOTA"),
					resulSet.getLong("PROPIETARIO"),
					resulSet.getLong("MEDICO"),
					resulSet.getString("MEDICAMENTO"),
					resulSet.getDate("FECHA"));
			resulSet.close();
			preparedStatement.close();
			return orderDtoReturn;
		}
		resulSet.close();
		preparedStatement.close();
		return null;
	}
}
