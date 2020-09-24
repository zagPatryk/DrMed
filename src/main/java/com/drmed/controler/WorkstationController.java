package com.drmed.controler;

import com.drmed.mapper.WorkstationMapper;
import com.drmed.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/workstation")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private WorkstationMapper workstationMapper;

    // ręcznie napsiany kod sql do pobierania workstaci wykonujących dany test

}
