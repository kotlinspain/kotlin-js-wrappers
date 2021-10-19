package samples.timepicker

import antd.timepicker.timePicker
import moment.Moment
import react.*
import styled.css
import styled.styledDiv

external interface ValueDemoState : State {
    var value: Moment?
}

class ValueDemo : RComponent<Props, ValueDemoState>() {
    private val handleChange = fun(time: Moment, _: String) {
        console.log(time)

        setState {
            value = time
        }
    }

    override fun ValueDemoState.init() {
        value = null
    }

    override fun RBuilder.render() {
        timePicker {
            attrs {
                value = state.value
                onChange = handleChange
            }
        }
    }
}

fun RBuilder.valueDemo() = child(ValueDemo::class) {}

fun RBuilder.value() {
    styledDiv {
        css { +TimePickerStyles.value }
        valueDemo()
    }
}
