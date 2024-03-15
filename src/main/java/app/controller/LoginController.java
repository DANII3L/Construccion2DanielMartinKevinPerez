package app.controller;

import java.util.Scanner;

import app.dto.PersonDto;
import app.service.VeterinaryService;
import app.validators.PersonValidator;

public class LoginController {
	private static Scanner reader = new Scanner(System.in);
	private static PersonValidator personInputValidator = new PersonValidator();
	private static VeterinaryService loginService = new VeterinaryService();
	private static SellerController sellerController = new SellerController();
	private static VeterinaryController veterinaryController = new VeterinaryController();
	private static AdminController adminController = new AdminController();

	public void login() throws Exception {
		System.out.println("ingrese su usuario");
		String userName = reader.nextLine();
		personInputValidator.userNameValidator(userName);
		System.out.println("ingrese su contraseña");
		String password = reader.nextLine();
		personInputValidator.passwordValidator(password);
		PersonDto personDto = new PersonDto(userName, password);
		loginService.login(personDto);
		loginRouter(personDto);
		loginService.logout();
	}

	private void loginRouter(PersonDto personDto) {
		if (personDto.getRol().equals("Veterinario"))
			veterinaryController.session();
		else if (personDto.getRol().equals("Vendedor"))
			sellerController.session();
		else if (personDto.getRol().equals("Administrador"))
			adminController.session();
		else if(personDto.getRol().equals("Dueño"))
			System.out.println("Rol dueños no tiene permisos para manejo de aplicativo.");
		else
			System.out.println("Rol no encontrado en el aplicativo.");
	}
}
