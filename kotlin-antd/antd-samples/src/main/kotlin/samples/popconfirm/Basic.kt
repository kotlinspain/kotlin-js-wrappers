package samples.popconfirm

import antd.MouseEvent
import antd.message.message
import antd.popconfirm.popconfirm
import org.w3c.dom.HTMLElement
import react.RBuilder
import react.dom.a
import styled.css
import styled.styledDiv

private val confirm: (e: MouseEvent<HTMLElement>?) -> Unit = { e ->
    console.log(e)

    message.success("Click on Yes")
}

private val cancel: (e: MouseEvent<HTMLElement>?) -> Unit = { e ->
    console.log(e)

    message.error("Click on No")
}

fun RBuilder.basic() {
    styledDiv {
        css { +PopconfirmStyles.basic }
        popconfirm {
            attrs {
                title = "Are you sure delete this task?"
                onConfirm = confirm
                onCancel = cancel
                okText = "Yes"
                cancelText = "No"
            }
            a {
                attrs.href = "#"
                +"Delete"
            }
        }
    }
}
