const $ = (select) => document.querySelector(select)

export class IdIsLogged {
	constructor() {
		this.email = $('#email')
		this.psw = $('#psw')
		this.submitButton = $('#submit')
		this.pswError = $('#passwordHelpBlock')
		this.emailError = $('#emailHelpBlock')
	}
}

export class IdIsRegister extends IdIsLogged {
	constructor() {
		super()
		this.newPsw = $('#npsw')
	}
}