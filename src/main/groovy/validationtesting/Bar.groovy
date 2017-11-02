package validationtesting

import grails.validation.Validateable

class Bar implements Validateable {
    String barField

    static constraints = {
        barField(nullable: false, blank: false)
    }
}
