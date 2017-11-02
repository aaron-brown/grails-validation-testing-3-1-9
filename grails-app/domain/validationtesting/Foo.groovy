package validationtesting

class Foo {

    String fooField

    static constraints = {
        fooField(nullable: false, blank: false)
    }
}
