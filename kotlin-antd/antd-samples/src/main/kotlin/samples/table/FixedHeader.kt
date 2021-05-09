package samples.table

import antd.pagination.*
import antd.table.*
import kotlinext.js.*
import react.*
import styled.*

private external interface FixedHeaderTableDataItem {
    var key: String
    var name: String
    var age: Number
    var address: String
}

private val tableColumns = arrayOf<ColumnType<FixedHeaderTableDataItem>>(
    jsObject {
        title = "Name"
        dataIndex = "name"
        width = 150
    },
    jsObject {
        title = "Age"
        dataIndex = "age"
        width = 150
    },
    jsObject {
        title = "Address"
        dataIndex = "address"
    }
)

private val tableData = (0..100).map { i ->
    jsObject<FixedHeaderTableDataItem> {
        key = "$i"
        name = "Edward King $i"
        age = 32
        address = "London, Park Lane no. $i"
    }
}.toTypedArray()

fun RBuilder.fixedHeader() {
    styledDiv {
        css { +TableStyles.fixedHeader }
        table<FixedHeaderTableDataItem, TableComponent<FixedHeaderTableDataItem>> {
            attrs {
                columns = tableColumns.unsafeCast<ColumnsType<FixedHeaderTableDataItem>>()
                dataSource = tableData
                pagination = jsObject<PaginationConfig> { pageSize = 50 }
                scroll = jsObject { y = 240 }
            }
        }
    }
}
