package validationtesting

import spock.lang.Specification
import spock.lang.Unroll

class BarSpec extends Specification {
    Bar bar

    def setup() {
        bar = new Bar()
    }

    @Unroll
    void 'Validate validate() == #expect (barField: #barFieldVerbose)'() {
        setup:
        bar.barField = barField

        expect:
        bar.validate() == expect
        bar.errors.getFieldError('barField')?.code == barFieldErrorCode

        where:
        expect << [
            true,

            false,
            false,
            false,
        ]

        [barField, barFieldErrorCode, barFieldVerbose] << [
            ['bar', null, "'bar'"],

            [null, 'nullable', 'null'],
            ['', 'blank', "''"],
            [' ', 'blank', "' '"],
        ]
    }

    @Unroll
    void 'Validate validate() == #expect (barField: #barFieldVerbose) (Setting barField via Constructor-args)'() {
        setup:
        bar = new Bar(barField: barField)

        expect:
        bar.validate() == expect
        bar.errors.getFieldError('barField')?.code == barFieldErrorCode

        where:
        expect << [
            true,

            false,
            false,
            false,
        ]

        [barField, barFieldErrorCode, barFieldVerbose] << [
            ['bar', null, "'bar'"],

            [null, 'nullable', 'null'],
            ['', 'blank', "''"],
            [' ', 'blank', "' '"],
        ]
    }
}
