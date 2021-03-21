package name.russkikh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/user/{id}").setViewName("show");
        registry.addViewController("/user/new").setViewName("new");
        registry.addViewController("/admin").setViewName("users");
        registry.addViewController("/user/{id}/edit").setViewName("edit");
    }
}