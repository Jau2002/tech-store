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
		this.newPsw = $('#nPsw')
		this.newPswError = $('#npswHelpBlock')
	}
}

export class IdIsChangePass {
	constructor() {
		this.submitButton = $('#submit')
		this.newPsw = $('#nPsw')
		this.conPsw = $('#cPsw')
		this.newPswError = $('#npswHelpBlock')
		this.conPswError = $('#conPassHelpBlock')
	}
}