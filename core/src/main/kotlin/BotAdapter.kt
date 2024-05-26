class BotAdapter(init: BotAdapter.() -> Unit) {

    private val bots = mutableListOf<Bot>()

    fun <Config: Any, PBot: Bot> use(plugin: Protocol<Config, PBot>, configuration: Config.() -> Unit) {
        bots.add(plugin.configure(configuration))
    }

    fun listen(listener: Event.() -> Unit) {
        bots.forEach {
            it.listen(listener)
        }
    }

    companion object {
    }
}

