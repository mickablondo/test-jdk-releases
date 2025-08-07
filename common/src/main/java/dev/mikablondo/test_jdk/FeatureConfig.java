package dev.mikablondo.test_jdk;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureConfig {

    private final JavaFeature javaFeature;

    public FeatureConfig(JavaFeature javaFeature) {
        this.javaFeature = javaFeature;
    }

    @PostConstruct
    public void init() {
        javaFeature.run();
    }
}
