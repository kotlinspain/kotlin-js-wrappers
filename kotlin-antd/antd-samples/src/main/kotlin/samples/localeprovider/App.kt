package samples.localeprovider

import kotlinx.css.*
import kotlinx.css.properties.borderTop
import react.RBuilder
import react.RComponent
import react.RProps
import react.State
import react.dom.h2
import styled.StyleSheet
import styled.css
import styled.styledDiv

object LocaleProviderStyles : StyleSheet("locale-provider", isStatic = true) {
    val container by css {}
    val basic by css {}
    val all by css {
        descendants(".locale-components") {
            paddingTop = 16.px
            borderTop(1.px, BorderStyle.solid, Color("#d9d9d9"))
        }
        descendants(".example") {
            margin(16.px, 0.px)
            children("*") {
                marginRight = 8.px
            }
        }
        descendants(".change-locale") {
            marginBottom = 16.px
        }
    }
}

class LocaleProviderApp : RComponent<RProps, State>() {
    override fun RBuilder.render() {
        h2 { +"LocaleProvider" }
        styledDiv {
            css { LocaleProviderStyles.container }
            basic()
            all()
        }
    }
}

fun RBuilder.localeProviderApp() = child(LocaleProviderApp::class) {}
