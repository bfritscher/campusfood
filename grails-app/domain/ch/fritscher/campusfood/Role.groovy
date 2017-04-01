package ch.fritscher.campusfood


class Role {

	String authority

	static mapping = {
		cache true
		id generator: 'sequence', params: [sequence: 'role_seq']
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
