package dev.mikablondo.test_jdk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("#{T(java.lang.Runtime).version().feature() >= 24}")
public class Java24Features implements JavaFeature {

    @Override
    public void run() {
        System.out.println("Test JDK 24 features");
    }
}
