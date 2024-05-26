import java.lang.reflect.Member

/** Universal Event
 * */

data class User(val id: String, val name: String?, val nick: String?, val avatar: String?, val isBot: Boolean?)

data class GuildMember(val user: User, val nick: String?, val avatar: String?, val joinAt: Long)

data class GuildRole(val id: String, val name: String?)

data class Guild(val id: String, val name: String?, val avatar: String?)

data class Channel(val id: String, val type: ChannelType, val name: String?, val parentId: String?)

data class Message(
    val id: String,
    val content: String,
    val channel: Channel?,
    val guild: Guild?,
    val member: GuildMember?,
    val user: User?,
    val createAt: Long?,
    val updateAt: Long?
)


enum class ChannelType {
    TEXT,
    DIRECT,
    CATEGORY,
    VOICE
}

class Event {

    var messageCreated = mutableListOf<(Channel, Message, User) -> MutableList<MessageElement>>()
    fun onMessageCreated(action: (Channel, Message, User) -> MutableList<MessageElement>) = messageCreated.add(action)
    /*
    val messageUpdated = mutableListOf<(Channel, Message, User) -> Unit>()
    fun onMessageUpdated(action: (Channel, Message, User) -> Unit) = messageUpdated.add(action)
    
    val messageDeleted = mutableListOf<(Channel, Message, User) -> Unit>()
    fun onMessageDeleted(action: (Channel, Message, User) -> Unit) = messageDeleted.add(action)
    
    val guildAdded = mutableListOf<(Guild) -> Unit>()
    fun onGuildAdded(action: (Guild) -> Unit) = guildAdded.add(action)
    
    val guildUpdated = mutableListOf<(Guild) -> Unit>()
    fun onGuildUpdated(action: (Guild) -> Unit) = guildUpdated.add(action)
    
    val guildRemoved = mutableListOf<(Guild) -> Unit>()
    fun onGuildRemoved(action: (Guild) -> Unit) = guildRemoved.add(action)
    
    val guildRequest = mutableListOf<(Guild) -> Unit>()
    fun onGuildRequest(action: (Guild) -> Unit) = guildRequest.add(action)
    
    val guildMemberAdded = mutableListOf<(Guild, Member, User) -> Unit>()
    fun onGuildMemberAdded(action: (Guild, Member, User) -> Unit) = guildMemberAdded.add(action)
    
    val guildMemberUpdated = mutableListOf<(Guild, Member, User) -> Unit>()
    fun onGuildMemberUpdated(action: (Guild, Member, User) -> Unit) = guildMemberUpdated.add(action)
    
    val guildMemberRemoved = mutableListOf<(Guild, Member, User) -> Unit>()
    fun onGuildMemberRemoved(action: (Guild, Member, User) -> Unit) = guildMemberRemoved.add(action)
    
    val guildMemberRequest = mutableListOf<(Guild, Member, User) -> Unit>()
    fun onGuildMemberRequest(action: (Guild, Member, User) -> Unit) = guildMemberRequest.add(action)
    
    val friendRequest = mutableListOf<(User) -> Unit>()
    fun onFriendRequest(action: (User) -> Unit) = friendRequest.add(action)
    */
}
