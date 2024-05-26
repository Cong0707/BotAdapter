fun main() {
    BotAdapter {
        use(Satori()) {
            host = "127.0.0.1"
            port = 5500
            token = "cong"
        }
        listen {
            onMessageCreated { _, _, _ ->
                send {
                    text { "hello" }
                }
            }
        }

    }
}