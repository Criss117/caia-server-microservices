package com.solidos.caia.users.services;

import com.solidos.caia.users.dtos.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void consume(Example example) {
    LOGGER.info(String.format("Message received -> %s", example.toString()));
  }
}
