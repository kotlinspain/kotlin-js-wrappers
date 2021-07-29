package samples.input

import antd.input.textArea
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.textarea() {
    styledDiv {
        css { +InputStyles.textarea }
        textArea {
            attrs.rows = 4
        }
    }
}
