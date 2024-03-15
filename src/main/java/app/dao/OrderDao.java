package app.dao;

import app.dto.OrderDto;

public interface OrderDao {
	public OrderDto createOrder(OrderDto orderDto) throws Exception;
	public boolean findOrderExist(OrderDto orderDto) throws Exception;
	public OrderDto searchOrder(OrderDto orderDto) throws Exception;
}
