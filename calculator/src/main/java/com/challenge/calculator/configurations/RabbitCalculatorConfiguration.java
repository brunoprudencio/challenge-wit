package com.challenge.calculator.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitCalculatorConfiguration {

	@Value("${calculator.queues}")
	private String queue;

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		return new RabbitTemplate( connectionFactory );
	}

	@Bean
	public Queue replyTo() {
		return new Queue( queue );
	}
}
