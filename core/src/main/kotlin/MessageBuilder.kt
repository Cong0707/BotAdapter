import java.awt.image.BufferedImage

interface MessageElement

class Text(val text: String): MessageElement

class Image(val image: BufferedImage): MessageElement

class MessageBuilder {
    val elements = mutableListOf<MessageElement>()

    fun text(element: Text) = elements.add(element)
    inline fun text(block: () -> String) = elements.add(Text(block()))

    fun img(element: Image) = elements.add(element)
    inline fun img(block: () -> BufferedImage) = elements.add(Image(block()))
}

fun send(msg: MessageBuilder.() -> Unit) = MessageBuilder().apply(msg).elements