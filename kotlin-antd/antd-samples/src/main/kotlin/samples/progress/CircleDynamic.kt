package samples.progress

import antd.*
import antd.button.*
import antd.button.button
import antd.icon.*
import antd.progress.progress
import react.*
import react.dom.div
import styled.*

external interface CircleDynamicAppState : RState {
    var percent: Number
}

class CircleDynamicApp : RComponent<RProps, CircleDynamicAppState>() {
    private val increase: MouseEventHandler<Any> = {
        var newPercent = state.percent.toInt() + 10

        if (newPercent > 100) {
            newPercent = 100
        }

        setState {
            percent = newPercent
        }
    }

    private val decline: MouseEventHandler<Any> = {
        var newPercent = state.percent.toInt() - 10

        if (newPercent < 0) {
            newPercent = 0
        }

        setState {
            percent = newPercent
        }
    }

    override fun CircleDynamicAppState.init() {
        percent = 0
    }

    override fun RBuilder.render() {
        div {
            progress {
                attrs {
                    type = "circle"
                    percent = state.percent
                }
            }
            buttonGroup {
                button {
                    attrs {
                        onClick = decline
                        icon = buildElement {
                            minusOutlined {}
                        }
                    }
                }
                button {
                    attrs {
                        onClick = increase
                        icon = buildElement {
                            plusOutlined {}
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.circleDynamicApp() = child(CircleDynamicApp::class) {}

fun RBuilder.circleDynamic() {
    styledDiv {
        css { +ProgressStyles.circleDynamic }
        circleDynamicApp()
    }
}
