package app.service;

import app.dto.OrderDto;

public interface OrderService {
	public OrderDto createOrdern(OrderDto orderDto) throws Exception;
	public OrderDto consultOrdern(OrderDto orderDto) throws Exception;
	public void cancelOrdern(OrderDto orderDto) throws Exception;
}
