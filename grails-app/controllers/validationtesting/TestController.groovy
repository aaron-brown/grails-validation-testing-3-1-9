package validationtesting

class TestController {

    def index() { 
        Foo foo = new Foo()
        foo.fooField = ' '

        Bar bar = new Bar()
        bar.barField = ' '

        render {
            p("Foo: ${foo.validate()}")
            p("Bar: ${bar.validate()}")
            p("new Foo(fooField: ' ').validate(): ${new Foo(fooField: ' ').validate()}")
            p("new Bar(barField: ' ').validate(): ${new Bar(barField: ' ').validate()}")
        }
    }
}
