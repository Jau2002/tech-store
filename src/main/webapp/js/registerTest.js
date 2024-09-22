const $ = (select) => document.querySelector(select)

class IdIsLogged {
	constructor() {
		this.email = $('#email')
		this.psw = $('#psw')
		this.submitButton = $('#submit')
		this.pswError = $('#passwordHelpBlock')
		this.emailError = $('#emailHelpBlock')
	}
}

const { email, psw, submitButton, newPsw, emailError } = new IdIsLogged()

const passwordValidation = (pass, err) => {
	let isValid = true

	if (isValid != false && !pass.value) {
		pass.focus()
		isValid = false
		err.style.display = 'block'
	} else if (
		isValid != false &&
		!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}$/.test(pass.value)
	) {
		pass.focus()
		isValid = false
		err.style.display = 'block'
	} else {
		err.style.display = 'none'
	}
	return isValid
}

const passwordConfirmValidation = (pass, compare, err) => {
	let isValid = false
	
	if (isValid != false && !pass.value) {
		pass.focus()
		isValid = false
		err.style.display = 'block'
	} else if (isValid != false && pass.value != compare.value) {
		pass.focus()
		isValid = false
		err.style.display = 'block'
	} else {
		err.style.display = 'none'
	}
	return isValid
}

function validateIsLogged() {
	let isValid = true

	if (!email.value) {
		email.focus()
		emailError.style.display = 'block'
		isValid = false
	} else if (!/^[a-z]+@unbosque\.edu\.co$/.test(email.value)) {
		email.focus()
		emailError.style.display = 'block'
		isValid = false
	} else {
		emailError.style.display = 'none'
	}
	isValid = passwordValidation(psw, pswError)
	return isValid
}

document.addEventListener('DOMContentLoaded', () => {
	email.addEventListener('input', validations.isRegister)
	psw.addEventListener('input', validations.isRegister)
	newPsw.addEventListener('input', validations.isRegister)

	submitButton.addEventListener('click', (event) => {
		event.preventDefault()
		if (validations.isRegister()) {
			console.log('Formulario enviado')
		}
	})
	validations.isRegister()
})
