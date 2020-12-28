package com.drmed.vaadin.service;

import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.vaadin.config.VaadinConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorServiceVaadin {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private VaadinConfig vaadinConfig;

    private Gson gson = new Gson();

    public DoctorDto postNewDoctor(NewDoctorDto newDoctorDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String object = gson.toJson(newDoctorDto);

        URI url = UriComponentsBuilder.fromHttpUrl(vaadinConfig.getBackendAddress() + vaadinConfig.getDoctorAddress() + "addNewDoctor")
                .build().encode().toUri();

        HttpEntity<String> request = new HttpEntity<String>(object, headers);

        return restTemplate.postForEntity(url, request, DoctorDto.class).getBody();
    }

    public List<DoctorInfoDto> getAllDoctors() {
        URI url = UriComponentsBuilder.fromHttpUrl(vaadinConfig.getBackendAddress() + vaadinConfig.getDoctorAddress() + "getAllDoctors")
                .build().encode().toUri();
        return Arrays.asList(restTemplate.getForEntity(url, DoctorInfoDto[].class).getBody());
    }

    public void updateDoctor(DoctorDto doctorDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String object = gson.toJson(doctorDto);

        URI url = UriComponentsBuilder.fromHttpUrl(vaadinConfig.getBackendAddress() + vaadinConfig.getDoctorAddress() + "updateDoctor")
                .build().encode().toUri();

        HttpEntity<String> request = new HttpEntity<String>(object, headers);

        restTemplate.put(url, request);
    }

    public DoctorDto getDoctorById(Long doctorId) {
        URI url = UriComponentsBuilder.fromHttpUrl(vaadinConfig.getBackendAddress() + vaadinConfig.getDoctorAddress() + "getDoctorById")
                .queryParam("doctorId", doctorId)
                .build().encode().toUri();
        return restTemplate.getForEntity(url, DoctorDto.class).getBody();
    }
}
