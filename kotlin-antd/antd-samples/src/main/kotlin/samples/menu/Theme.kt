package samples.menu

import antd.icon.appstoreOutlined
import antd.icon.mailOutlined
import antd.icon.settingOutlined
import antd.menu.*
import antd.switch.switch
import kotlinext.js.js
import org.w3c.dom.events.MouseEvent
import react.*
import react.dom.br
import react.dom.div
import react.dom.span
import styled.css
import styled.styledDiv

external interface ThemeSiderState : State {
    var theme: MenuTheme
    var current: String
}

class ThemeSider : RComponent<Props, ThemeSiderState>() {
    private val changeTheme = fun(checked: Boolean, _: MouseEvent) {
        setState {
            theme = if (checked) "dark" else "light"
        }
    }

    private val handleClick: MenuClickEventHandler = { info ->
        console.log("click ", info)

        setState {
            current = info.key as String
        }
    }

    override fun ThemeSiderState.init() {
        theme = "dark"
        current = "1"
    }

    override fun RBuilder.render() {
        div {
            switch {
                attrs {
                    checked = state.theme == "dark"
                    onChange = changeTheme
                    checkedChildren = "Dark"
                    unCheckedChildren = "Light"
                }
            }
            br {}
            br {}
            menu {
                attrs {
                    theme = state.theme
                    onClick = handleClick
                    style = js { width = 256 }
                    defaultOpenKeys = arrayOf("sub1")
                    selectedKeys = arrayOf(state.current)
                    mode = "inline"
                }
                subMenu {
                    attrs {
                        key = "sub1"
                        title = buildElement {
                            span {
                                mailOutlined {}
                                span { +"Navigation One" }
                            }
                        }
                    }
                    menuItem {
                        attrs.key = "1"
                        +"Option 1"
                    }
                    menuItem {
                        attrs.key = "2"
                        +"Option 2"
                    }
                    menuItem {
                        attrs.key = "3"
                        +"Option 3"
                    }
                    menuItem {
                        attrs.key = "4"
                        +"Option 4"
                    }
                }
                subMenu {
                    attrs {
                        key = "sub2"
                        title = buildElement {
                            span {
                                appstoreOutlined {}
                                span { +"Navigation Two" }
                            }
                        }
                    }
                    menuItem {
                        attrs.key = "5"
                        +"Option 5"
                    }
                    menuItem {
                        attrs.key = "6"
                        +"Option 6"
                    }
                    subMenu {
                        attrs {
                            key = "sub3"
                            title = "Submenu"
                        }
                        menuItem {
                            attrs.key = "7"
                            +"Option 7"
                        }
                        menuItem {
                            attrs.key = "8"
                            +"Option 8"
                        }
                    }
                }
                subMenu {
                    attrs {
                        key = "sub4"
                        title = buildElement {
                            span {
                                settingOutlined {}
                                span { +"Navigation Three" }
                            }
                        }
                    }
                    menuItem {
                        attrs.key = "9"
                        +"Option 9"
                    }
                    menuItem {
                        attrs.key = "10"
                        +"Option 10"
                    }
                    menuItem {
                        attrs.key = "11"
                        +"Option 11"
                    }
                    menuItem {
                        attrs.key = "12"
                        +"Option 12"
                    }
                }
            }
        }
    }
}

fun RBuilder.themeSider() = child(ThemeSider::class) {}

fun RBuilder.theme() {
    styledDiv {
        css { +MenuStyles.theme }
        themeSider()
    }
}
