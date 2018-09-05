package org.grails.samples

class PetController {

	def petclinicService

	def add() {
		if (request.method == 'GET') {
			return [pet: new Pet(owner: Owner.get(params.owner?.id)), types: PetType.list()]
		}

		request.session.setAttribute("add", new Date())

		def pet = petclinicService.createPet(params.pet_name, params.pet?.birthDate,
			(params.pet?.type?.id ?: 0) as Long, (params.pet_owner_id ?: 0) as Long)

		println("PET3: "+pet+" : "+pet.getClass().getName())

		def externalPet = new ExternalPet(
				name: pet.name,
				owner: pet.owner.firstName,
				type: pet.type.name,
				birthDate: pet.birthDate)

		println("** EXTERNAL PET: "+externalPet)

		request.session.setAttribute("external_pet", externalPet);

		def r = petclinicService.getRegion()
		r.put(request.session.id, externalPet)

		if (pet.hasErrors()) {
			return [pet: pet, types: PetType.list()]
		}

		redirect controller: 'owner', action: 'show', id: pet.owner.id
	}

	def edit() {
		if (request.method == 'GET') {
			render view: 'add', model: [pet: Pet.get(params.id), types: PetType.list()]
			return
		}

		def pet = Pet.get(params.id)

		petclinicService.updatePet(pet, params.pet_name, params.pet?.birthDate,
			(params.pet?.type?.id ?: 0) as Long, (params.pet_owner_id ?: 0) as Long)

		if (pet.hasErrors()) {
			render view: 'add', model: [pet: pet, types: PetType.list()]
		}
		else {
			redirect controller: 'owner', action: 'show', id: pet.owner.id
		}
	}

	def addVisit() {
		if (request.method == 'GET') {
			return [visit: new Visit(pet: Pet.get(params.id))]
		}

		def visit = petclinicService.createVisit((params.visit?.pet?.id ?: 0) as Long, params.visit?.description, params.visit?.date)
		if (visit.hasErrors()) {
			return [visit: visit]
		}

		redirect controller: 'owner', action: 'show', id: visit.pet.owner.id
	}
}
