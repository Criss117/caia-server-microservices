package com.solidos.caia.users.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.solidos.caia.users.application.ports.input.Example;

@Service
public class RabbitMQProducer {

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(Example example) {
    LOGGER.info(String.format("Message sent -> %s", example.toString()));

    rabbitTemplate.convertAndSend(exchange, routingKey, example);
  }
}
