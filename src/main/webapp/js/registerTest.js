import { IdIsRegister } from './selectors'
import { validations } from './validations'

const { email, psw, submitButton, newPsw } = new IdIsRegister()

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
