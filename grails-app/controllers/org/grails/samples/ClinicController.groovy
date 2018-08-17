package org.grails.samples

class ClinicController {

	def petclinicService

	def index() {
		[add: request.session.getAttribute("add"),
		petname: petclinicService.getPetFromCache(request)]
	}

	def tutorial() {}

	def vets() {
		[vets: Vet.list()]
	}
}
