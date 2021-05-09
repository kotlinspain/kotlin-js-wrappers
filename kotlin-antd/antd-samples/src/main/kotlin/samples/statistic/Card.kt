package samples.statistic

import antd.card.*
import antd.grid.*
import antd.grid.col
import antd.icon.*
import antd.statistic.*
import kotlinext.js.*
import kotlinx.css.background
import kotlinx.css.padding
import kotlinx.css.px
import react.*
import styled.*

fun RBuilder.card() {
    styledDiv {
        css { +StatisticStyles.card }
        styledDiv {
            css {
                background = "#ECECEC"
                padding(30.px)
            }
            row {
                attrs.gutter = 16
                col {
                    attrs.span = 12
                    card {
                        statistic {
                            attrs {
                                title = "Active"
                                value = 11.28
                                precision = 2
                                valueStyle = js { color = "#3f8600" }
                                prefix = buildElement {
                                    arrowUpOutlined {}
                                }
                                suffix = "%"
                            }
                        }
                    }
                }
                col {
                    attrs.span = 12
                    card {
                        statistic {
                            attrs {
                                title = "Idle"
                                value = 9.3
                                precision = 2
                                valueStyle = js { color = "#cf1322" }
                                prefix = buildElement {
                                    arrowDownOutlined {}
                                }
                                suffix = "%"
                            }
                        }
                    }
                }
            }
        }
    }
}
