package global.din.pulsartest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import java.util.function.Consumer

@SpringBootApplication
class PulsarTestApplication() {

    @Bean
    fun testListener(): Consumer<Message<TestMessage>> = Consumer {
            message ->
        println(message.payload)
    }

    @JvmRecord
    data class TestMessage(val message: String)
}

fun main(args: Array<String>) {
    runApplication<PulsarTestApplication>(*args)
}
