package global.din.pulsartest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

@SpringBootApplication
class PulsarTestApplication() {


//    @Bean
//    fun timeSupplier(): Supplier<Time> {
//        return Supplier { Time(System.currentTimeMillis().toString()) }
//    }

    @Bean
    fun timeProcessor(): Consumer<Message<Time>> = Consumer {
        message ->
        val temp = message.payload
        println(temp)
    }

    @JvmRecord
    data class Time(val time: String)

    @JvmRecord
    data class EnhancedTime(val time: Time, val extra: String) {}

    @RestController
    class MessageResource(var bridge: StreamBridge) {
        @GetMapping("/")
        fun index(): String{
            bridge.send("timeSupplier-out-0",  Time(System.currentTimeMillis().toString()))
            return "String"
        }
    }

}

fun main(args: Array<String>) {
    runApplication<PulsarTestApplication>(*args)

}
