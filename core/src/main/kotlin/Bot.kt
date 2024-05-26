interface Bot {
    fun listen(event: Event.() -> Unit)

    fun start()
}