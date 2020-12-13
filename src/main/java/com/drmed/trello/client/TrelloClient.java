package com.drmed.trello.client;

import com.drmed.trello.config.TrelloConfig;
import com.drmed.trello.dto.TrelloListDto;
import com.drmed.trello.newObjects.NewTrelloBoardDto;
import com.drmed.trello.newObjects.NewTrelloCardDto;
import com.drmed.trello.response.CreatedTrelloBoardDto;
import com.drmed.trello.response.CreatedTrelloCardDto;
import com.drmed.trello.dto.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class TrelloClient {
    @Autowired
    private TrelloConfig trelloConfig;
    @Autowired
    private RestTemplate restTemplate;

    public CreatedTrelloBoardDto createNewBoard(NewTrelloBoardDto newTrelloBoardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/boards/")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", newTrelloBoardDto.getName())
                .queryParam("desc", newTrelloBoardDto.getDescription())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloBoardDto.class);
    }

    public CreatedTrelloCardDto createNewCard(NewTrelloCardDto newTrelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", newTrelloCardDto.getName())
                .queryParam("desc", newTrelloCardDto.getDescription())
                .queryParam("idList", newTrelloCardDto.getListId())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);
    }

    public TrelloCardDto getTrelloCardById(String cardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId)
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .build().encode().toUri();
        return restTemplate.getForObject(url, TrelloCardDto.class);
    }

    public TrelloCardDto updateCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + trelloCardDto.getId())
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, TrelloCardDto.class);
    }

    public void deleteCard(String cardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId)
                .build().encode().toUri();
        restTemplate.delete(url);
    }

    public TrelloListDto[] getAllListsOnBoard(String boardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/boards/" + boardId + "/lists/")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .build().encode().toUri();
        return restTemplate.getForObject(url, TrelloListDto[].class);
    }

    public TrelloCardDto[] getAllCardsOnList(String listId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/lists/" + listId + "/cards/")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .build().encode().toUri();
        return restTemplate.getForObject(url, TrelloCardDto[].class);
    }
}
