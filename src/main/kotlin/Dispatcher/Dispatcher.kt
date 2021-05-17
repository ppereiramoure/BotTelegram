package Dispatcher

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.*
import com.github.kotlintelegrambot.entities.dice.DiceEmoji
import com.github.kotlintelegrambot.entities.inlinequeryresults.InlineQueryResult
import com.github.kotlintelegrambot.entities.inlinequeryresults.InputMessageContent
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.network.fold


fun main() {
    val bot = bot {


    /**Introducimos el token del bot*/
    token = Apikey.miToken



    /**El dispatch nos permite enviar información a Telegram para manejar el bot*/
    dispatch {


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

        /**
         * Comando que tras recibir hola envia un string de saludo
         */

        command("hola") {

            val result = bot.sendMessage(chatId = ChatId.fromId(update.message!!.chat.id), text = "Buenos dias," +
                    " menos si eres facha, si eres facha no.")

            result.fold(
                {

                },
                {
                    // do something with the error
                }
            )
        }

        /**
         * Comando que tras recibir adios se despide envia un string de despedida.
         */
        command("adios") {

            val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Idi Nahui")

            result.fold({

            }, {
                // do something with the error
            })
        }

        message(Filter.Sticker) {
            bot.sendMessage(ChatId.fromId(message.chat.id), text = "Bonito sticker, pero no tanto como" +
                    " Vladimir Putin de carne y hueso <3")
        }

        message(Filter.Reply or Filter.Forward) {
            bot.sendMessage(ChatId.fromId(message.chat.id), text = "No le repliques a la madre patria.")
        }
        //Altera el texto sin necesidad de un editor de texto. Solo sirve para texto incluido en el comando
        command("markdown") {
            val markdownText = "_Mi gran mensaje_: *Markdown* es `util a su manera`"
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = markdownText,
                parseMode = ParseMode.MARKDOWN
            )
        }

        /**
         * Comando con markdown que tras recibir importante enviara un enlace tambien.
         */

        command("importante") {
            val markdownV2Text = """
                    *negrita*
                    _italic_
                    __subrallado__
                    ~tachado~
                    *negrita _italic negrita ~italic negrita tachada~ __subrallado italic negrita___ negrita*
                    [Lo realmente importante](https://www.youtube.com/watch?v=WhPvJOnHotE&ab_channel=PaceAudio)
                    [inline mention of a user](tg://user?id=123456789) 
                    `inline fixed-width code`
                    ```kotlin
                    fun main() {
                        println("Hello Kotlin!")
                    }
                    ```
                """.trimIndent()
            /*trimIndent() ->
            Detecta una sangría mínima común de todas las líneas de entrada, la elimina de cada línea y también
            elimina la primera y la última línea si están en blanco (observe la diferencia en blanco vs vacío).
            Tenga en cuenta que las líneas en blanco no afectan el nivel de sangría detectado. En caso de que haya
            líneas que no estén en blanco sin espacios en blanco iniciales (sin sangría), la sangría común es 0 y,
            por lo tanto, esta función no cambia la sangría. No conserva los finales de línea originales.
             */

            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = markdownV2Text,
                parseMode = ParseMode.MARKDOWN_V2
            )
        }
        command("inlinebuttons") {
            val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                //text es el texto que aparece en el boton
                //callbackData Retoena el callbackQuery asociado (linea de codigo )
                listOf(InlineKeyboardButton.CallbackData(text = "Press", callbackData = "pizdiec")),
                //text es el texto que aparece en el boton
                //callbackData Retoena el callbackQuery asociado (linea de codigo )
                listOf(InlineKeyboardButton.CallbackData(text = "Privet", callbackData = "Privet tovarich"))
            )
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = "Mis botones, si hay algun rojo mejor no lo pulses",
                replyMarkup = inlineKeyboardMarkup
            )
        }
        /**
         * @param f Nombre del comando de nuestro bot
         * Este comando retorna un  texto y dos botones situados en donde el usuario puede escribir
         */
        /*
        Con esto le envias tu direccion al bot y tu numero de telefono. Solo funciona por privado con el bot, en
        un grupo no funciona este comando
        */
        command("f") {
            val keyboardMarkup = KeyboardReplyMarkup(keyboard = generateUsersButton(), resizeKeyboard = true)
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = "Aqui los botones tovarich",
                replyMarkup = keyboardMarkup
            )
        }
        /**
         * @param foticos Nombre del comando de nuestro bot
         * Este comando retorna un texto y fotos
         */
        //Este comando sirve para poder enviar fotos con el bot
        command("foticos") {
            bot.sendMediaGroup(
                chatId = ChatId.fromId(message.chat.id),
                mediaGroup = MediaGroup.from(
                    InputMediaPhoto(
                        media = TelegramFile.ByUrl("https://wallpapercave.com/w/wp3090364g"),
                        caption = "Blyat UwU"
                    ),
                    InputMediaPhoto(
                        media = TelegramFile.ByUrl("https://wallpapercave.com/w/wp3090396"),
                        caption = "Amerikkka"
                    )
                ),
                replyToMessageId = message.messageId
            )
        }
        /**
         * @param pizdiec Es el texto que aparece en el collbackData del boton asociado
         * Retorna el callbackQuery
         */
        //este texto de entrada tiene que coincidir con el texto que se le ponga al boton en callbackData
        callbackQuery("pizdiec") {
            val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
            bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
        }
        /**
         * @param Quierovodka Es el texto que aparece en el collbackData del boton asociado
         * Retorna el callbackQuery
         */
        //este texto de entrada tiene que coincidir con el texto que se le ponga al boton EN callbackData
        callbackQuery(
            callbackData = "Quierovodka",
            callbackAnswerText = "Para soportar el frío invierno siberiano",
            callbackAnswerShowAlert = true
        ) {
            val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
            bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
        }
        // si pones /quiero te devielve un texto
        text("quiero") {
            bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Mi 10")
        }
        //te devuelve la latituid y longitud de te localizacion
        location {
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = "Tu localizacion es (latitud=${location.latitude}, longitud=${location.longitude})",
                replyMarkup = ReplyKeyboardRemove()
            )
        }
        //devuelve un mensaje de texto con nuetro nombre
        contact {
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = "Buenas, ${contact.firstName} ${contact.lastName}",
                replyMarkup = ReplyKeyboardRemove()
            )
        }

        channel {
            // Handle channel update
        }

        inlineQuery {
            val queryText = inlineQuery.query

            if (queryText.isBlank() or queryText.isEmpty()) return@inlineQuery

            val inlineResults = (0 until 5).map {
                InlineQueryResult.Article(
                    id = it.toString(),
                    title = "$it. $queryText",
                    inputMessageContent = InputMessageContent.Text("$it. $queryText"),
                    description = "Add $it. before you word"
                )
            }
            bot.answerInlineQuery(inlineQuery.id, inlineResults)
        }
        //Cuando envias una foto te devuelve un texto. En un grupo tendrias que responderle a un comentario del bot
        photos {
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = "Muy buena foto"
            )
        }
        /**
         * @param dado nombre del comando
         * Retorna un dado/diana en formato emoji que saca una puntuacion
         */
        command("dado") {
            bot.sendDice(ChatId.fromId(message.chat.id), DiceEmoji.Dartboard)
        }
        //cuando reenvias la diana a el bot por privado te devuelve la puntiacion
        dice {
            bot.sendMessage(ChatId.fromId(message.chat.id), "Un dado ${dice.emoji.emojiValue} con valor ${dice.value} ha sido recivido")
        }
        //sale cuando suceda un error
        telegramError {
            println(error.getErrorMessage())
        }
    }

    }
    bot.startPolling()
}


/**
 * Retorna los botones generados
 */

fun generateUsersButton(): List<List<KeyboardButton>> {
    return listOf(
        listOf(KeyboardButton("Te digo tu localizacion (no lo soporta el ordenador)", requestLocation = true)),
        listOf(KeyboardButton("Envio tu numero de contacto ", requestContact = true))
    )
}


