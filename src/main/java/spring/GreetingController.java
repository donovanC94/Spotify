package spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
	
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/token")
    public Greeting greeting(@RequestParam(value="code", defaultValue="") String name, @RequestParam(value="state", defaultValue="") String state) {
        return new Greeting(counter.incrementAndGet(), name, state);
    }

}