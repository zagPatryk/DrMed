package com.drmed.api.trello.mapper;

import com.drmed.base.orderedTest.mapper.OrderedTestMapper;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrelloMapper {
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private VisitMapper visitMapper;

}
