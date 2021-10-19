package samples.image

import react.RBuilder
import react.RComponent
import react.Props
import react.State
import react.dom.h2
import styled.StyleSheet
import styled.css
import styled.styledDiv

object ImageStyles : StyleSheet("image", isStatic = true) {
    val container by css {}
    val basic by css {}
    val fallback by css {}
    val placeholder by css {}
}

class ImageApp : RComponent<Props, State>() {
    override fun RBuilder.render() {
        h2 { +"Image" }
        styledDiv {
            css { +ImageStyles.container }
            basic()
            fallback()
            placeholder()
        }
    }
}

fun RBuilder.imageApp() = child(ImageApp::class) {}
