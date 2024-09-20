import { IdIsLogged } from './selectors'

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

export function updateButtonState() {
	submitButton.disabled = !validateIsLogged()
}