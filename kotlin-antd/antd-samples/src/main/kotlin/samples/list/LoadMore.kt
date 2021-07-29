package samples.list

import antd.MouseEventHandler
import antd.avatar.avatar
import antd.button.button
import antd.list.ListComponent
import antd.list.list
import antd.list.listItem
import antd.list.listItemMeta
import antd.skeleton.skeleton
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import org.w3c.dom.events.Event
import react.*
import react.dom.a
import react.dom.div
import styled.css
import styled.styledDiv
import kotlin.js.json

private val count = 3
private val fakeDataUrl = "https://randomuser.me/api/?results=${count}&inc=name,gender,email,nat&noinfo"

external interface LoadMoreListDataItem {
    var loading: Boolean
    var name: dynamic
}

external interface LoadMoreListState : State {
    var initLoading: Boolean
    var loading: Boolean
    var data: Array<LoadMoreListDataItem>
    var list: Array<LoadMoreListDataItem>
}

class LoadMoreList : RComponent<RProps, LoadMoreListState>() {
    private val getData = fun(callback: (Any) -> Unit) {
        window.fetch(fakeDataUrl, jsObject {
            method = "GET"
            headers = json().apply {
                this["Content-Type"] = "application/json"
            }
        }).then { res -> res.json() }.then { res -> callback(res!!) }
    }

    private val onLoadMore: MouseEventHandler<Any> = {
        setState {
            loading = true
            list = state.data.plus(arrayOf(count).map {
                js {
                    loading = true
                    name = emptyArray<Any>()
                }.unsafeCast<LoadMoreListDataItem>()
            })
        }

        getData { res ->
            val newData = state.data.plus(res.asDynamic().results.unsafeCast<Array<LoadMoreListDataItem>>())

            setState(jsObject<LoadMoreListState> {
                data = newData
                list = newData
                loading = false
            }) {
                // Resetting window's offsetTop so as to display react-virtualized demo underfloor.
                // In real scene, you can using public method of react-virtualized:
                // https://stackoverflow.com/questions/46700726/how-to-use-public-method-updateposition-of-react-virtualized
                window.dispatchEvent(Event("resize"))
            }
        }
    }

    override fun componentDidMount() {
        getData { res ->
            setState {
                initLoading = false
                data = res.asDynamic().results.unsafeCast<Array<LoadMoreListDataItem>>()
                list = res.asDynamic().results.unsafeCast<Array<LoadMoreListDataItem>>()
            }
        }
    }

    override fun LoadMoreListState.init() {
        initLoading = false
        loading = false
        data = emptyArray()
        list = emptyArray()
    }

    override fun RBuilder.render() {
        val listLoadMore = if (!state.initLoading && !state.loading) {
            buildElement {
                styledDiv {
                    css {
                        textAlign = TextAlign.center
                        marginTop = 12.px
                        height = 32.px
                        lineHeight = LineHeight("32px")
                    }
                    button {
                        attrs.onClick = onLoadMore
                        +"loading more"
                    }
                }
            }
        } else null

        list<LoadMoreListDataItem, ListComponent<LoadMoreListDataItem>> {
            attrs {
                className = "demo-loadmore-list"
                loading = state.initLoading
                itemLayout = "horizontal"
                loadMore = listLoadMore
                dataSource = state.list
                renderItem = { item, _ ->
                    buildElement {
                        listItem {
                            attrs.actions = arrayOf(
                                buildElement { a { +"edit" } },
                                buildElement { a { +"more" } }
                            )
                            skeleton {
                                attrs {
                                    avatar = true
                                    title = false
                                    loading = item.loading
                                    active = true
                                }
                                listItemMeta {
                                    attrs {
                                        avatar = buildElement {
                                            avatar {
                                                attrs.src = "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                                            }
                                        }
                                        title = buildElement {
                                            a {
                                                attrs.href = "https://ant.design"
                                                +"${item.name["last"] ?: ""}"
                                            }
                                        }
                                        description = "Ant Design, a design language for background applications, is refined by Ant UED Team"
                                    }
                                }
                                div { +"content" }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.loadMoreList() = child(LoadMoreList::class) {}

fun RBuilder.loadMore() {
    styledDiv {
        css { +ListStyles.loadMore }
        loadMoreList()
    }
}
