package samples

import kotlinext.js.*
import kotlinx.js.Record
import kotlinx.js.jso
import react.*
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.p
import reactintl.components.provider.IntlProvider
import reactintl.components.useIntl
import kotlin.js.Date
import kotlin.random.*

private val providerMessages = js {
    simple = "Hello world"
    placeholder = "Hello {name}"
    date = "Hello {ts, date}"
    time = "Hello {ts, time}"
    number = "Hello {num, number}"
    plural = "I have {num, plural, one {# dog} other {# dogs}}"
    select = "I am a {gender, select, male {boy} female {girl} other {}}"
    selectordinal = """I am the {order, selectordinal,
        one {#st person}
        two {#nd person}
        =3 {#rd person}
        other {#th person}
    }""".trimIndent()
    richtext = "I have <bold>{num, plural, one {# dog} other {# dogs}}</bold>"
    richertext = "I have <bold>{num, plural, one {# & dog} other {# dogs}}</bold>"
    unicode = "Hello\u0020{placeholder}"
}.unsafeCast<Any>()

private external interface DemoProps : Props {
    var currentTime: Date
}

private val Demo = FC<DemoProps> {
    val intl = useIntl()

    p {
        +intl.formatMessage(jso { id = "simple" })
        br {}
        +intl.formatMessage(jso { id = "placeholder" }, js { name = "John" }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "date" }, js { ts = Date.now() }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "time" }, js { ts = Date.now() }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "number" }, js { num = Random.nextInt() * 1000 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "plural" }, js { num = 1 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "plural" }, js { num = 99 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "select" }, js { gender = "male" }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "select" }, js { gender = "female" }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "selectordinal" }, js { order = 1 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "selectordinal" }, js { order = 2 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "selectordinal" }, js { order = 3 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "selectordinal" }, js { order = 4 }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso { id = "unicode" }, js { this.placeholder = "world" }.unsafeCast<Record<String, Any>>())
        br {}
        +intl.formatMessage(jso {
            id = "whatever"
            defaultMessage = "Hello\u0020{placeholder}"
        }, js { this.placeholder = "world" }.unsafeCast<Record<String, Any>>())
    }
}

val Hooks = FC<Props> {
    IntlProvider {
        locale = "en"
        messages = providerMessages
        Demo { currentTime = Date() }
    }
}
