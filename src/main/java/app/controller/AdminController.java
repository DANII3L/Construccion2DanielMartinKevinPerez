package app.controller;

import java.util.Scanner;

import app.dto.PersonDto;
import app.service.VeterinaryService;
import app.validators.PersonValidator;

public class AdminController {
	private static VeterinaryService veterinaryService = new VeterinaryService();
	private static Scanner reader = new Scanner(System.in);
	private static PersonValidator personValidator = new PersonValidator();
	private static final String MENU = "ingrese\n1.Para crear usuario\n2.Para cerrar Sesion";
	
	private void createUser() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personValidator.fullNameValidator(rol);
		System.out.println("ingrese la edad del usuario");
		int age = personValidator.ageValidator(reader.nextLine());
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personValidator.fullNameValidator(password);
		PersonDto personDto = new PersonDto(id, fullName, rol, userName, password, age);
		veterinaryService.createUser(personDto, "Administrador");
	}
	
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

	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1": {
			createUser();
			return true;
		}
		case "2": 
			return false;
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}
}
