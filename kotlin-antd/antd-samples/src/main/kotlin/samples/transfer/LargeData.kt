package samples.transfer

import antd.MouseEvent
import antd.MouseEventHandler
import antd.transfer.TransferComponent
import antd.transfer.TransferItem
import antd.transfer.transfer
import kotlinext.js.js
import kotlinext.js.jso
import react.*
import styled.css
import styled.styledDiv
import kotlin.random.Random

external interface LargeDataTransferItem : TransferItem {
    var chosen: Boolean
}

external interface LargeDataAppState : State {
    var mockData: Array<TransferItem>
    var targetKeys: Array<String>
}

class LargeDataApp : RComponent<Props, LargeDataAppState>() {
    private val getMock: MouseEventHandler<Any> = {
        val keys = mutableListOf<String>()
        val data = mutableListOf<TransferItem>()

        for (i in 0..19) {
            val item = jso<LargeDataTransferItem> {
                key = i.toString()
                title = "content${i + 1}"
                description = "description of content${i + 1}"
                chosen = Random.nextDouble() * 2 > 1
            }

            if (item.chosen) {
                keys.add(item.key!!)
            }

            data.add(item)
        }

        setState {
            mockData = data.toTypedArray()
            targetKeys = keys.toTypedArray()
        }
    }

    private val handleChange = fun(nextTargetKeys: Array<String>, direction: String, moveKeys: Any) {
        console.log(nextTargetKeys, direction, moveKeys)

        setState {
            targetKeys = nextTargetKeys
        }
    }

    override fun componentDidMount() {
        getMock(js {}.unsafeCast<MouseEvent<Any>>())
    }

    override fun LargeDataAppState.init() {
        mockData = emptyArray()
        targetKeys = emptyArray()
    }

    override fun RBuilder.render() {
        transfer<TransferItem, TransferComponent<TransferItem>> {
            attrs {
                dataSource = state.mockData
                targetKeys = state.targetKeys
                onChange = handleChange
                render = { item -> item.title!! }
            }
        }
    }
}

fun RBuilder.largeDataApp() = child(LargeDataApp::class) {}

fun RBuilder.largeData() {
    styledDiv {
        css { +TransferStyles.largeData }
        largeDataApp()
    }
}
