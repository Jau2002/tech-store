import { validations } from './validations'

document.addEventListener('DOMContentLoaded', () => {
	newPsw.addEventListener('input', validations.isChange)
	conPsw.addEventListener('input', validations.isChange)

	submitButton.addEventListener('click', (event) => {
		event.preventDefault()
		if (validations.isChange()) {
			console.log('Formulario enviado')
		}
	})
	validations.isChange()
})