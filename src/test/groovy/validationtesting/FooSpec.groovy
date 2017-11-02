package validationtesting

import grails.test.mixin.TestMixin
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin([GrailsUnitTestMixin, HibernateTestMixin])
@Domain(Foo)
class FooSpec extends Specification {
    Foo foo

    def setup() {
        foo = new Foo()
    }

    @Unroll
    void 'Validate validate() == #expect (fooField: #fooFieldVerbose)'() {
        setup:
        foo.fooField = fooField

        expect:
        foo.validate() == expect
        foo.errors.getFieldError('fooField')?.code == fooFieldErrorCode

        where:
        expect << [
            true,

            false,
            false,
            false,
        ]

        [fooField, fooFieldErrorCode, fooFieldVerbose] << [
            ['foo', null, "'foo'"],

            [null, 'nullable', 'null'],
            ['', 'blank', "''"],
            [' ', 'blank', "' '"],
        ]
    }

    @Unroll
    void 'Validate validate() == #expect (fooField: #fooFieldVerbose) (Setting fooField via Constructor-args)'() {
        setup:
        foo = new Foo(fooField: fooField)

        expect:
        foo.validate() == expect
        foo.errors.getFieldError('fooField')?.code == fooFieldErrorCode

        where:
        expect << [
            true,

            false,
            false,
            false,
        ]

        [fooField, fooFieldErrorCode, fooFieldVerbose] << [
            ['foo', null, "'foo'"],

            [null, 'nullable', 'null'],
            ['', 'nullable', "''"],
            [' ', 'nullable', "' '"],
        ]
    }
}
