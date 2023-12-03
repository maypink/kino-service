package kino.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("exchange-example-4");
        return rabbitTemplate;
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("getAllFilms");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("addNewFilmWithId");
    }

    @Bean
    public Queue myQueue3() {
        return new Queue("addNewFilmWithTitleAndYear");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange-example-4");
    }

    @Bean
    public Binding errorBinding1(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("getAllFilms");
    }

    @Bean
    public Binding errorBinding2(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("addNewFilmWithId");
    }

    @Bean
    public Binding errorBinding3(){
        return BindingBuilder.bind(myQueue3()).to(directExchange()).with("addNewFilmWithTitleAndYear");
    }
}