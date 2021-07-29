package samples.mentions

import antd.mentions.OptionProps
import antd.mentions.mentions
import antd.mentions.option
import kotlinext.js.js
import react.RBuilder
import styled.css
import styled.styledDiv

private fun handleChange(value: Any) {
    console.log("Change: ", value)
}

private val handleSelect = { option: OptionProps, _: String ->
    console.log("select", option)
}

fun RBuilder.basic() {
    styledDiv {
        css { MentionsStyles.basic }
        mentions {
            attrs {
                style = js { width = "100%" }
                onChange = ::handleChange
                onSelect = handleSelect
            }
            option {
                attrs.value = "afc163"
                +"afc163"
            }
            option {
                attrs.value = "zombieJ"
                +"zombieJ"
            }
            option {
                attrs.value = "yesmeck"
                +"yesmeck"
            }
        }
    }
}
