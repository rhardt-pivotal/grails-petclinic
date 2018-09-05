package org.grails.samples

class ClinicController {

	def petclinicService

	def index() {
		def petname = petclinicService.getPetFromCache(request)
		def petclass = petname ? petname.getClass().getName() : "no-class"
		[
				add: request.session.getAttribute("add"),
				petname: petname ? petname.name : "not-found",
				petclass: petclass
		]
	}

	def tutorial() {}

	def vets() {
		[vets: Vet.list()]
	}
}
