package WebHook

import Apikey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.entities.files.File
import com.github.kotlintelegrambot.network.fold
import com.github.kotlintelegrambot.webhook

object MyBotConfig {
    const val API_TOKEN = Apikey
    const val SERVER_HOSTNAME = "https://webhook.site/a9936e8c-e2d5-4c66-8e59-a88df655fefb"
}

fun main() {
    val bot = bot {
        token = MyBotConfig.API_TOKEN
        webhook {
            url = "${MyBotConfig.SERVER_HOSTNAME}/${MyBotConfig.API_TOKEN}"
            /* This certificate argument is only needed when you want Telegram to trust your
            * self-signed certificates. If you have a CA trusted certificate you can omit it.
            * More info -> https://core.telegram.org/bots/webhooks */
            certificate = TelegramFile.ByFile(File(Files.certPath))
            maxConnections = 50
            allowedUpdates = listOf("message")
        }
        dispatch {
            command("hello") {
                bot.sendMessage(ChatId.fromId(message.chat.id), "Buenas")
            }
        }
    }
    bot.startWebhook()
}