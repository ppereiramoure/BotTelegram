import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main() {
    val bot = bot {
        token = Apikey.miToken
        dispatch {
            text {
                print("Chat ID: ")
                println(message.chat.id)
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = text)
            }
        }
    }

}