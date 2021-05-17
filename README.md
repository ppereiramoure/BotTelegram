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
