import { IdIsRegister, IdIsLogged, IdIsChangePass } from './selectors'

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

const { pswError, newPswError } = new IdIsRegister()

function validateIsRegister() {
	let isValid = validateIsLogged()
	isValid = passwordConfirmValidation(newPsw, psw, newPswError)
	return isValid
}

const { conPsw, conPswError } = new IdIsChangePass()

function validateIsChange() {
	let isValid = passwordValidation(newPsw, newPswError)
	isValid = passwordConfirmValidation(conPsw, newPsw, conPswError)
	return isValid
}

export const validations = {
	isLogged: () => (submitButton.disabled = !validateIsLogged()),
	isRegister: () => (submitButton.disabled = !validateIsRegister()),
	isChange: () => (submitButton.disabled = !validateIsChange()),
}