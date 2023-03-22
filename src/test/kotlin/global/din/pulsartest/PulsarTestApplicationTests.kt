package global.din.pulsartest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.function.StreamBridge

@SpringBootTest
class PulsarTestApplicationTests {

    @Autowired
    lateinit var bridge: StreamBridge
	
    @Test
    fun dlqTest() {
        bridge.send("test", PulsarTestApplication.TestMessage("pulsar test message"))
        Thread.sleep(10000)
    }
}
