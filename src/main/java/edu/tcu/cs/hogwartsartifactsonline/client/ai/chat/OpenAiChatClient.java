package edu.tcu.cs.hogwartsartifactsonline.client.ai.chat;

import org.springframework.web.client.RestClient;

public class OpenAiChatClient implements ChatClient{

    private final RestClient restClient;


    public OpenAiChatClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(endpoint)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    @Override
    public ChatResponse generate(ChatRequest chatRequest) {
        return null;
    }

}
