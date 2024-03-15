package app.controller;

import java.util.List;
import java.util.Scanner;

import app.dto.FactureDto;
import app.dto.HistoryClinicalDto;
import app.dto.OrderDto;
import app.service.VeterinaryService;
import app.validators.FactureValidator;
import app.validators.OrderValidator;
import app.validators.PersonValidator;
import app.validators.PetValidator;

public class SellerController {
	private static VeterinaryService veterinaryService = new VeterinaryService();
	private static Scanner reader = new Scanner(System.in);
	private static PersonValidator personValidator = new PersonValidator();
	private static PetValidator petValidator = new PetValidator();
	private static FactureValidator factureValidator = new FactureValidator();
	private static OrderValidator orderValidator = new OrderValidator();
	private static final String MENU = "ingrese\n1.Venta de medicamentos\n2.Para busqueda de orden\n3.Para cerrar Sesion";

	public void session() {
		boolean runApp = true;
		while (runApp) {
			try {
				System.out.println(MENU);
				String option = reader.nextLine();
				runApp = menu(option);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void sellProducts() throws Exception {
		System.out.println("ingrese el id de la mascota");
		int petId = petValidator.petIdValidator(reader.nextLine());
		System.out.println("ingrese el id del dueño");
		long ownerId = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		System.out.println("ingrese nombre de/los medicamento/s");
		String nameMedicine = reader.nextLine();
		factureValidator.medicineNameValidator(nameMedicine);
		System.out.println("ingrese el valor de/los medicamento/s");
		double costMedicine = factureValidator.costValidator(reader.nextLine());
		System.out.println("ingrese la cantidad de/los medicamento/s");
		int amountMedicine = factureValidator.amountValidator(reader.nextLine());
		FactureDto factureDto = new FactureDto(petId, ownerId, orderId, nameMedicine, costMedicine, amountMedicine);

		veterinaryService.createFacture(factureDto);
	}

	public void searchOrder() throws Exception {
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		OrderDto orderSearch = veterinaryService.consultOrdern(new OrderDto(orderId));
		if (orderSearch != null) {
			System.out.println("Orden encontrada:");
			System.out.println("Mascota: " + orderSearch.getPetId());
			System.out.println("Cédula de dueño: " + orderSearch.getOwnerID());
			System.out.println("Veterinario: " + orderSearch.getVetId());
			System.out.println("Nombre de medicamento/s: " + orderSearch.getMedicineName());
			System.out.println("Fecha de registro: " + orderSearch.getDateRegister());
			List<HistoryClinicalDto> listHistorySearch = veterinaryService
					.consultHistoryClinical(new HistoryClinicalDto(orderSearch.getId()));
			for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
				if (HistoryDtoSearch.getOrderCancellation())
					System.out.println("El estado de la orden es cancelado.");
			System.out.println("-------------------------------------");
		} else
			System.out.println("No se ha encontrado una orden");
	}

	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1":
			sellProducts();
			return true;
		case "2":
			searchOrder();
			return true;
		case "3":
			return false;
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}
}
