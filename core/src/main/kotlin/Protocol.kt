public interface Protocol<Config : Any, PBot: Bot> {

    public fun configure(block: Config.() -> Unit): PBot

    public fun load(bot: PBot)
}
