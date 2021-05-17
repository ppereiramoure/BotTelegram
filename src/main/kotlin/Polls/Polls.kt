package Polls

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.pollAnswer
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.polls.PollType
import com.github.kotlintelegrambot.network.fold

fun main() {
    /**Instanciamos un objeto bot*/
    val bot = bot {

        /**Introducimos el token del bot*/
        /**Introducimos el token del bot*/
        token = Apikey.miToken

        /**El dispatch nos permite enviar información a Telegram para manejar el bot*/

        /**El dispatch nos permite enviar información a Telegram para manejar el bot*/
        dispatch {


            text {

                print("Chat ID: ")
                println(message.chat.id)
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = text)
            }

            /**Creamos un comando que devolvera el mensaje que indiquemos*/

            /**Creamos un comando que devolvera el mensaje que indiquemos*/
            command("start") {

                val result = bot.sendMessage(chatId = ChatId.fromId(update.message!!.chat.id), text = "Bot sovietico iniciado")

                result.fold(
                    {

                    },
                    {
                        // do something with the error
                    }
                )
            }

            pollAnswer {
                println("${pollAnswer.user.username} has selected the option ${pollAnswer.optionIds.lastOrNull()} in the poll ${pollAnswer.pollId}")
            }
            command("regularpoll") {
                bot.sendPoll(

                    /**Recoge la id del usuario que haya votado en la encuesta*/
                    chatId = ChatId.fromId(message.chat.id),
                    question = "Te gusta el Vodka?",
                    options = listOf("Si", "No"),
                    isAnonymous = false
                )
            }
            command("quizpoll") {
                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),

                    /**Define el tipo de encuesta*/
                    type = PollType.QUIZ,

                    /**Defines la pregunta*/
                    question = "Que vodka es ruso?",

                    /**Describes la respuestas*/
                    options = listOf("Smirnoff", "Absolut", "Knebep"),

                    /**Indicas la opcion correcta*/
                    correctOptionId = 0,

                    /**Defines si es anonimo*/
                    isAnonymous = false
                )
            }
            command("closedpoll") {
                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),
                    type = PollType.QUIZ,
                    question = "Cablagar o Osos?",
                    options = listOf("Cabalgar", "Osos", "Cabalgar Osos"),
                    correctOptionId = 2,
                    explanation = "Cerrado"
                )
            }
        }
    }
    bot.startPolling()
}

