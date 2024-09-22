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

const { email, psw, submitButton, emailError, pswError } = new IdIsLogged()

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

	if (isValid != false && !psw.value) {
		psw.focus()
		isValid = false
		pswError.style.display = 'block'
	} else if (
		isValid != false &&
		!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}$/.test(psw.value)
	) {
		psw.focus()
		isValid = false
		pswError.style.display = 'block'
	} else {
		pswError.style.display = 'none'
	}

	return isValid
}

const validations = {
	isLogged: () => (submitButton.disabled = !validateIsLogged()),
}

document.addEventListener('DOMContentLoaded', () => {
	email.addEventListener('input', validations.isLogged)
	psw.addEventListener('input', validations.isLogged)

	submitButton.addEventListener('click', (event) => {
		event.preventDefault()
		if (validations.isLogged()) {
			console.log('Formulario enviado')
		}
	})
	validations.isLogged()
})
