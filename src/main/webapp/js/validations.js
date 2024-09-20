import { IdIsRegister, IdIsLogged } from './selectors'

const { email, psw, submitButton, newPsw, emailError } = new IdIsLogged()

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

const { pswError, newPswError } = new IdIsRegister()

function validateIsRegister() {
	let isValid = validateIsLogged()

	if (isValid != false && !newPsw.value) {
		newPsw.focus()
		isValid = false
		newPswError.style.display = 'block'
	} else if (isValid != false && newPsw.value != psw.value) {
		newPsw.focus()
		isValid = false
		newPswError.style.display = 'block'
	} else {
		newPswError.style.display = 'none'
	}

	return isValid
}

export const validations = {
	isLogged: () => (submitButton.disabled = !validateIsLogged()),
	isRegister: () => (submitButton.disabled = !validateIsRegister()),
}