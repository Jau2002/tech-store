import { IdIsLogged } from './selectors'
import { validations } from './validations'

const { email, psw, submitButton } = new IdIsLogged()

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
