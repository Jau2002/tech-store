import { IdIsLogged } from './selectors'
import { updateButtonState } from './validattions'

const { email, psw, submitButton } = new IdIsLogged()

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
