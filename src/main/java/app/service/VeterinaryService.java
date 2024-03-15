package app.service;

import java.util.Arrays;
import java.util.List;

import app.dao.PersonDaoImpl;
import app.dao.PetDao;
import app.dao.PetDaoImp;
import app.dao.HistoryClinicalDaoImp;
import app.dao.HistoryClinicalDao;
import app.dao.PersonDao;
import app.dao.FactureDao;
import app.dao.FactureDaoImp;
import app.dao.LoginDao;
import app.dao.LoginDaoImp;
import app.dao.OrderDao;
import app.dao.OrderDaoImp;
import app.dto.FactureDto;
import app.dto.HistoryClinicalDto;
import app.dto.OrderDto;
import app.dto.PersonDto;
import app.dto.PetDto;
import app.dto.SessionDto;

public class VeterinaryService implements VeterinarianService, LoginService, HistorialClinicalService, OrderService {
	List<String> rolesAdmin = Arrays.asList("Vendedor", "Veterinario");
	String rolVet = "Dueño";
	private static long sessionId = 0L;

	@Override
	public void createUser(PersonDto personDto, String rol) throws Exception {
		if (rol.equals("Administrador")) {
			if (!rolesAdmin.contains(personDto.getRol()))
				throw new Exception("el rol no es valido");
		} else {
			if (!rolVet.equals(personDto.getRol()))
				throw new Exception("el rol no es valido");
		}

		PersonDao personDao = new PersonDaoImpl();
		if (personDao.findUserExist(personDto))
			throw new Exception("ya existe un usuario con esa cedula");

		if (personDao.existUserByUserName(personDto))
			throw new Exception("ya existe el usuario");

		personDao.createPerson(personDto);
		System.out.println("se ha creado el usuario");
	}

	@Override
	public void createFacture(FactureDto factureDto) throws Exception {
		validateSessionGeneral();

		List<HistoryClinicalDto> listHistorySearch = consultHistoryClinical(
				new HistoryClinicalDto(factureDto.getOrderId()));
		for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
			if (HistoryDtoSearch.getOrderCancellation())
				System.out.println(
						"No se puede realizar la venta de medicamentos debido a que la orden a sido cancelada.");

		PetDao petDao = new PetDaoImp();
		if (!petDao.findPetExist(new PetDto(factureDto.getPetId())))
			throw new Exception("No existe la mascota.");

		PersonDao personDao = new PersonDaoImpl();
		PersonDto ownerSearch = personDao.findUserById(new PersonDto(factureDto.getOwnerId()));
		if (ownerSearch == null)
			throw new Exception("No existe el dueño.");
		else if(!ownerSearch.getRol().equals("Dueño"))
			throw new Exception("El usuario selecciona no es dueño de mascotas, tiene el rol de " + ownerSearch.getRol() +".");

		OrderDao orderDao = new OrderDaoImp();
		if (!orderDao.findOrderExist(new OrderDto(factureDto.getOrderId())))
			throw new Exception("No existe la orden.");

		FactureDao factureDao = new FactureDaoImp();
		factureDao.createFacture(factureDto);
		System.out.println("se ha creado la factura");
	}

	@Override
	public void setSesionID(long sesionId) {
		sessionId = sesionId;
	}

	@Override
	public void login(PersonDto personDto) throws Exception {
		PersonDao personDao = new PersonDaoImpl();
		PersonDto personDtoValidate = personDao.findUserByUserName(personDto);
		if (personDtoValidate == null)
			throw new Exception("usuario no valido");

		if (!personDto.getPassword().equals(personDtoValidate.getPassword()))
			throw new Exception("usuario o contraseña incorrectos");

		personDto.setRol(personDtoValidate.getRol());
		LoginDao loginDao = new LoginDaoImp();
		SessionDto sesionDto = loginDao.login(personDtoValidate);
		setSesionID(sesionDto.getId());
		System.out.println("se inicia la sesion " + sessionId);
	}

	@Override
	public void logout() throws Exception {
		LoginDao loginDao = new LoginDaoImp();
		loginDao.logout(sessionId);
		setSesionID(0);
	}

	@Override
	public void createPet(PetDto petDto) throws Exception {
		PersonDao personDao = new PersonDaoImpl();
		if (!personDao.findUserExist(new PersonDto(petDto.getOwnerId())))
			throw new Exception("No existe el dueño.");

		PetDao petDao = new PetDaoImp();
		petDao.createPet(petDto);
		System.out.println("Se ha creado la mascota");
	}

	@Override
	public List<HistoryClinicalDto> consultHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		HistoryClinicalDao historyDao = new HistoryClinicalDaoImp();
		return historyDao.findHistoryByOrden(historyClinicalDto);
	}

	private void validateSessionGeneral() throws Exception {
		SessionDto sessionDto = new LoginDaoImp().findSessionById(sessionId);
		if (sessionDto == null)
			throw new Exception("No hay una sesion valida");
	}

	@Override
	public void createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception {
		PetDao petDao = new PetDaoImp();

		PetDto petDto = petDao.findById(new PetDto(historyClinicalDto.getPetId()));
		if (petDto == null)
			throw new Exception("No existe la mascota.");

		PersonDao personDao = new PersonDaoImpl();
		if (!personDao.findUserExist(new PersonDto(historyClinicalDto.getVetId())))
			throw new Exception("No existe el veterinario.");

		OrderDto orderCreate = createOrdern(new OrderDto(historyClinicalDto.getPetId(), petDto.getOwnerId(),
				historyClinicalDto.getVetId(), historyClinicalDto.getMedicament(), historyClinicalDto.getDate()));

		historyClinicalDto.setOrderID(orderCreate.getId());

		// Creación de historia clinica
		HistoryClinicalDao historyDao = new HistoryClinicalDaoImp();
		historyDao.createHistory(historyClinicalDto);
		System.out.println("Se ha creado la historia clinica");
	}

	public OrderDto consultOrdern(OrderDto orderDto) throws Exception {
		OrderDao orderDao = new OrderDaoImp();

		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		if (orderSearch == null)
			System.out.println("No se ha encontrado una orden");

		return orderSearch;
	}

	@Override
	public void cancelOrdern(OrderDto orderDto) throws Exception {
		OrderDao orderDao = new OrderDaoImp();

		OrderDto orderSearch = orderDao.searchOrder(new OrderDto(orderDto.getId()));
		if (orderSearch == null)
			System.out.println("No se ha encontrado una orden");

		HistoryClinicalDao historyDao = new HistoryClinicalDaoImp();
		historyDao.cancelOrder(new HistoryClinicalDto(orderSearch.getId()));
	}

	@Override
	public OrderDto createOrdern(OrderDto orderDto) throws Exception {
		OrderDao orderDao = new OrderDaoImp();
		OrderDto orderCreate = orderDao.createOrder(orderDto);
		if (orderCreate == null)
			throw new Exception("No se ha creado la orden.");
		return orderCreate;
	}
}
