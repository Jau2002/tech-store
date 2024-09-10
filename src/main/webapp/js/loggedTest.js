const $ = (select) => document.querySelector(select)

class IdIsLogged {
	constructor() {
		this.email = $('#email')
		this.psw = $('#psw')
		this.submitButton = $('#submit')
	}
}

const { email, psw, submitButton } = new IdIsLogged()

function validateIsLogged() {
	let isValid = true

	if (!email.value) {
		email.focus()
		console.log('El campo no puede estar vacío.')
		isValid = false
	} else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
		email.focus()
		console.log('El email tiene un formato invalido')
		isValid = false
	}

	if (isValid != false && !psw.value) {
		psw.focus()
		console.log('el campo no puede estar vacío')
		isValid = false
	} else if (
		isValid != false &&
		!/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.* ).{8,16}$/.test(psw.value)
	) {
		psw.focus()
		console.log('El password tiene un formato invalido')
		isValid = false
	}

	return isValid
}

function updateButtonState() {
	submitButton.disabled = !validateIsLogged()
}

document.addEventListener('DOMContentLoaded', () => {
	email.addEventListener('input', updateButtonState)
	psw.addEventListener('input', updateButtonState)

	submitButton.addEventListener('click', (event) => {
		event.preventDefault()
		if (validateIsLogged()) {
			console.log('Formulario enviado')
		}
	})
	updateButtonState()
})
