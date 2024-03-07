package app.controller;

import java.util.Scanner;

import app.dto.PersonDto;
import app.service.VeterinaryService;
import app.validators.PersonValidator;

public class VeterinaryController {
	private static PersonValidator personValidator = new PersonValidator();
	private static Scanner reader = new Scanner(System.in);
	private static VeterinaryService veterinaryService = new VeterinaryService();
	private static final String MENU = "ingrese\n1.Para crear usuario\n2.Para crear Material\n3.Para cerrar Sesion";

	private void createUser() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personValidator.fullNameValidator(rol);
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personValidator.fullNameValidator(password);
		PersonDto personDto = new PersonDto(id, fullName, rol, userName, password);
		veterinaryService.createUser(personDto);
	}

	private void sellProducts() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personValidator.fullNameValidator(rol);
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personValidator.fullNameValidator(password);
		PersonDto personDto = new PersonDto(id, fullName, rol, userName, password);
		veterinaryService.createUser(personDto);
	}

	public void session() {
		boolean runApp = true;
		while (runApp) {
			try {
				System.out.println(MENU);
				String option = reader.nextLine();
				runApp=menu(option);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	private boolean menu(String option) throws Exception{
		switch (option) {
		case "1":{
			createUser();
			return true;
		}
		case "2": {
			sellProducts();
			return true;
		}
		case "3": {
			return false;
		}
		default :{
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}

}
