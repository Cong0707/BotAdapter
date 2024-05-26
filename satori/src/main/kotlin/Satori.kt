import com.github.nyayurn.yutori.next.SatoriEventService
import com.github.nyayurn.yutori.next.WebSocketEventService
import java.io.File
import java.util.*
import javax.imageio.ImageIO

class Satori : Protocol<SatoriConfig, SatoriBot> {
    override fun configure(block: SatoriConfig.() -> Unit): SatoriBot {
        val config = SatoriConfig().apply(block)
        return SatoriBot(config)
    }

    override fun load(bot: SatoriBot) {
        bot.start()
    }

}

class SatoriBot(val config: SatoriConfig) : Bot {
    lateinit var bot: SatoriEventService
    override fun listen(event: Event.() -> Unit) {
        bot = WebSocketEventService.of {
            properties {
                host = config.host
                port = config.port
                path = config.path
                token = config.token
                version = config.version
            }
            listeners {
                val events = Event().apply(event)
                events.messageCreated.forEach {
                    message.created { action, event ->
                        val msg = it(
                            Channel(
                                event.channel.id,
                                if (event.channel.type.number == 0) ChannelType.TEXT
                                else if (event.channel.type.number == 1) ChannelType.VOICE
                                else if (event.channel.type.number == 2) ChannelType.CATEGORY
                                else ChannelType.DIRECT, event.channel.name, event.channel.parent_id
                            ),
                            Message(event.message.id, event.message.content.toString(),
                                event.message.channel?.id?.let { it1 ->
                                    Channel(
                                        it1,
                                        if (event.message.channel?.type?.number == 0) ChannelType.TEXT
                                        else if (event.message.channel?.type?.number == 1) ChannelType.VOICE
                                        else if (event.message.channel?.type?.number == 2) ChannelType.CATEGORY
                                        else ChannelType.DIRECT,
                                        event.message.channel?.name,
                                        event.message.channel?.parent_id
                                    )
                                },
                                event.message.guild?.id?.let { it1 ->
                                    Guild(
                                        it1,
                                        event.message.guild?.name,
                                        event.message.guild?.avatar
                                    )
                                },
                                event.message.member?.user?.id?.let { it1 ->
                                    User(
                                        it1,
                                        event.message.member?.user?.name,
                                        event.message.member?.user?.nick,
                                        event.message.member?.user?.avatar,
                                        event.message.member?.user?.is_bot
                                    )
                                }
                                    ?.let { it2 ->
                                        event.message.member?.joined_at?.toLong()?.let { it1 ->
                                            GuildMember(
                                                it2, event.message.member?.nick, event.message.member?.avatar, it1
                                            )
                                        }
                                    },
                                event.message.user?.id?.let { it1 ->
                                    User(
                                        it1,
                                        event.message.user?.name,
                                        event.message.user?.nick,
                                        event.message.user?.avatar,
                                        event.message.user?.is_bot
                                    )
                                }, event.message.created_at?.toLong(), event.message.updated_at?.toLong()
                            ),
                            User(
                                event.user.id,
                                event.user.name,
                                event.user.nick,
                                event.user.avatar,
                                event.user.is_bot
                            )
                        )
                        action.message.create {
                            channel_id = event.channel.id
                            content {
                                msg.forEach {
                                    if (it is Text) text { it.toString() }
                                    else if (it is Image) {
                                        val file = File.createTempFile(System.currentTimeMillis().toString(), ".png")
                                        try {
                                            ImageIO.write(it.image, "png", file)
                                            val base64 = Base64.getEncoder().encodeToString(file.readBytes())
                                            img { src = "data:image/jpeg;base64,$base64" }
                                        } finally {
                                            file.delete()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun start() {
        bot.connect()
    }
}

class SatoriConfig {
    var host: String = "127.0.0.1"
    var port: Int = 5500
    var path: String = ""
    var token: String? = null
    var version: String = "v1"
}