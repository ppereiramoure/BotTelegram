## BotTelegram

###Indice

> 1. Echo
> 2. Polls
> 3. Dispacher
> 4. Si pero no
> 5. Bot

###Echo
Si el echo esta activado, reproduce cualquier mensaje que le hayamos indicado despues de introducir un comando

~~~
command("start") {
         val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Bot sovietico iniciado")
}
~~~

![Imagen Echo](https://github.com/ppereiramoure/BotTelegram/blob/master/Imagenes/Echo.png)

###Polls
Las polls se tratan de encuestas en las que podemos votar en encuestas de opinion o contestar a preguntas. Existen de dos tipos, las QUIZ (Preguntas con una respuesta correcta) y las REGULAR (Pueden o no tener una respuesta correcta)

~~~
command("quizpoll") {
                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),
                    type = PollType.QUIZ,
                    question = "Que vodka es ruso?",
                    options = listOf("Smirnoff", "Absolut", "Knebep"),
                    correctOptionId = 0,
                    isAnonymous = false
                )
            }
~~~

![Imagen Polls](https://github.com/ppereiramoure/BotTelegram/blob/master/Imagenes/2polls.png)
![Imagen Polls2](https://github.com/ppereiramoure/BotTelegram/blob/master/Imagenes/ClosedPolls.png)


###Dispacher


