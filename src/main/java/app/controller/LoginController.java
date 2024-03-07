package app.controller;

import java.util.Scanner;

import app.dto.PersonDto;
import app.service.VeterinaryService;
import app.service.LoginService;
import app.validators.PersonValidator;

public class LoginController {
	private static Scanner reader = new Scanner(System.in);
	private static PersonValidator personInputValidator = new PersonValidator();
	private static VeterinaryService loginService = new VeterinaryService();
	private static VeterinaryController veterinaryController = new VeterinaryController();

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
		if (!personDto.getRol().equals("Dueño"))
			veterinaryController.session();
		else
			System.out.println("Rol dueños no tiene permisos para manejo de aplicativo.");
	}
}
