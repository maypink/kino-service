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
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplateExchangeFilms() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("exchange-films");
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
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
    public Queue myQueue4(){return new Queue("getUserByUsername");}

    @Bean
    public Queue myQueue5(){return new Queue("getAllFilmRatingsByUsernameFF");}

    @Bean
    public Queue myQueue6(){return new Queue("addFilmRatingForUsernameAndTmdbId");}

    @Bean
    public Queue myQueue7(){return new Queue("getAllFilmRatingsByUsernameForRecommendations");}

    @Bean
    public Queue myQueue8(){return new Queue("getAllFilmsForRecommendations");}

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange-films");
    }

    @Bean
    public Binding getAllFilms(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("getAllFilms");
    }

    @Bean
    public Binding addNewFilmWithId(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("addNewFilmWithId");
    }

    @Bean
    public Binding addNewFilmWithTitleAndYear(){
        return BindingBuilder.bind(myQueue3()).to(directExchange()).with("addNewFilmWithTitleAndYear");
    }

    @Bean
    public Binding getAllFilmRatingsByUsername(){
        return BindingBuilder.bind(myQueue5()).to(directExchange()).with("getAllFilmRatingsByUsernameFF");
    }

    @Bean
    public Binding addFilmRatingForUsernameAndTmdbId(){
        return BindingBuilder.bind(myQueue6()).to(directExchange()).with("addFilmRatingForUsernameAndTmdbId");
    }

}