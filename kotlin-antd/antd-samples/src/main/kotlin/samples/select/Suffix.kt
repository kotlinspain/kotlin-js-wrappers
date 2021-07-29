package samples.select

import antd.icon.icon
import antd.icon.mehOutlined
import antd.select.SelectComponent
import antd.select.option
import antd.select.select
import kotlinext.js.js
import react.RBuilder
import react.buildElement
import react.dom.div
import styled.css
import styled.styledDiv

private val smileIcon = buildElement {
    icon {
        //attrs.type = "smile"
    }
}

private val mehIcon = buildElement {
    mehOutlined {}
}

private val handleChange = { value: String, _: Any ->
    console.log("selected $value")
}

fun RBuilder.suffix() {
    styledDiv {
        css { +SelectStyles.suffix }
        div {
            select<String, SelectComponent<String>> {
                attrs {
                    suffixIcon = smileIcon
                    defaultValue = "lucy"
                    style = js { width = 120 }
                    onChange = handleChange
                }
                option {
                    attrs.value = "jack"
                    +"Jack"
                }
                option {
                    attrs.value = "lucy"
                    +"Lucy"
                }
                option {
                    attrs {
                        value = "disabled"
                        disabled = true
                    }
                    +"Disabled"
                }
                option {
                    attrs.value = "yiminghe"
                    +"Yiminghe"
                }
            }
            select<String, SelectComponent<String>> {
                attrs {
                    suffixIcon = mehIcon
                    defaultValue = "lucy"
                    style = js { width = 120 }
                    disabled = true
                }
                option {
                    attrs.value = "lucy"
                    +"Lucy"
                }
            }
        }
    }
}
