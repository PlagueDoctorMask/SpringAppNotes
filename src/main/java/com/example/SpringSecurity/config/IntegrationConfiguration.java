package com.example.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class IntegrationConfiguration {

    @Bean
    public MessageChannel inputTextChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputTextChannel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "inputTextChannel", outputChannel = "outputTextChannel")
    public GenericTransformer<String,String> testTransformer(){
        return text ->{
            text.trim();
            return text;
        };
    }


    @Bean
    @ServiceActivator(inputChannel = "outputTextChannel")
    public FileWritingMessageHandler textHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("C:\\Users\\Dmitriy\\IdeaProjects\\SpringSecurity"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }


}
