package com.drmed.api.trello.newObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewTrelloCardDto {
    private String name;
    private String description;
    private String listId;
}
