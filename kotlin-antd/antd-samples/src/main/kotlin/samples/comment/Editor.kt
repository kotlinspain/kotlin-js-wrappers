package samples.comment

import antd.ChangeEventHandler
import antd.MouseEventHandler
import antd.avatar.avatar
import antd.button.button
import antd.comment.CommentProps
import antd.comment.comment
import antd.form.formItem
import antd.input.textArea
import antd.list.ListComponent
import antd.list.list
import kotlinext.js.jsObject
import kotlinx.browser.window
import moment.moment
import org.w3c.dom.HTMLTextAreaElement
import react.*
import react.dom.div
import react.dom.p
import styled.css
import styled.styledDiv

external interface CommentListProps : Props {
    var comments: Array<CommentProps>
}

class CommentList : RComponent<CommentListProps, State>() {
    override fun RBuilder.render() {
        list<CommentProps, ListComponent<CommentProps>> {
            attrs {
                dataSource = props.comments
                header = "${props.comments.size} ${if (props.comments.size > 1) "replies" else "reply"}"
                itemLayout = "horizontal"
                renderItem = { item, _ ->
                    buildElement {
                        comment {
                            attrs {
                                author = item.author
                                avatar = item.avatar
                                content = item.content
                                datetime = item.datetime
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.commentList(handler: RHandler<CommentListProps>) = child(CommentList::class, handler)

external interface EditorProps : Props {
    var onChange: ChangeEventHandler<HTMLTextAreaElement>
    var onSubmit: MouseEventHandler<Any>
    var submitting: Boolean
    var value: String?
}

class Editor : RComponent<EditorProps, State>() {
    override fun RBuilder.render() {
        div {
            formItem {
                textArea {
                    attrs {
                        rows = 4
                        onChange = props.onChange
                        value = props.value
                    }
                }
            }
            formItem {
                button {
                    attrs {
                        htmlType = "submit"
                        loading = props.submitting
                        onClick = props.onSubmit
                    }
                    +"Add Comment"
                }
            }
        }
    }
}

fun RBuilder.editor(handler: RHandler<EditorProps>) = child(Editor::class, handler)

external interface EditorAppState : State {
    var comments: Array<CommentProps>
    var submitting: Boolean
    var value: String?
}

class EditorApp : RComponent<Props, EditorAppState>() {
    private val handleSubmit: MouseEventHandler<Any> = {
        if (!state.value.isNullOrEmpty()) {
            setState {
                submitting = true
            }

            window.setTimeout({
                setState {
                    submitting = false
                    value = ""
                    comments = arrayOf<CommentProps>(
                        jsObject {
                            author = "Han Solo"
                            avatar = "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                            content = buildElement {
                                p { +"${state.value}" }
                            }
                            datetime = moment().fromNow()
                        }
                    ).plus(state.comments)
                }
            }, 1000)
        }
    }

    private val handleChange: ChangeEventHandler<HTMLTextAreaElement> = { e ->
        e.persist()

        setState {
            value = e.target.asDynamic().value as String
        }
    }

    override fun EditorAppState.init() {
        comments = emptyArray()
        submitting = false
        value = ""
    }

    override fun RBuilder.render() {
        div {
            if (state.comments.isNotEmpty()) {
                commentList {
                    attrs.comments = state.comments
                }
            }
            comment {
                attrs {
                    avatar = buildElement {
                        avatar {
                            attrs {
                                src = "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                                alt = "Han Solo"
                            }
                        }
                    }
                    content = buildElement {
                        editor {
                            attrs {
                                onChange = handleChange
                                onSubmit = handleSubmit
                                submitting = state.submitting
                                value = state.value
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.editorApp() = child(EditorApp::class) {}

fun RBuilder.editor() {
    styledDiv {
        css { +CommentStyles.editor }
        editorApp()
    }
}
