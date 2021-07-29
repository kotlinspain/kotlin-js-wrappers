package samples.tabs

import antd.tabs.tabPane
import antd.tabs.tabs
import kotlinx.html.classes
import react.RBuilder
import react.dom.div
import react.dom.p
import react.key
import styled.css
import styled.styledDiv

fun RBuilder.cardTop() {
    styledDiv {
        css { +TabsStyles.cardTop }
        div {
            attrs.classes = setOf("card-container")
            tabs {
                attrs.type = "card"
                tabPane {
                    attrs {
                        tab = "Tab Title 1"
                        key = "1"
                    }
                    p { +"Content of Tab Pane 1" }
                    p { +"Content of Tab Pane 1" }
                    p { +"Content of Tab Pane 1" }
                }
                tabPane {
                    attrs {
                        tab = "Tab Title 2"
                        key = "2"
                    }
                    p { +"Content of Tab Pane 2" }
                    p { +"Content of Tab Pane 2" }
                    p { +"Content of Tab Pane 2" }
                }
                tabPane {
                    attrs {
                        tab = "Tab Title 3"
                        key = "3"
                    }
                    p { +"Content of Tab Pane 3" }
                    p { +"Content of Tab Pane 3" }
                    p { +"Content of Tab Pane 3" }
                }
            }
        }
    }
}
