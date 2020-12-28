package com.drmed.vaadin.doctor;

import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.vaadin.main.MainView;
import com.drmed.vaadin.service.DoctorServiceVaadin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "all-doctors", layout = MainView.class)
@PageTitle("All doctors")
@CssImport("./styles/views/personform/person-form-view.css")
public class AllDoctorGrid extends Div {
    @Autowired
    private DoctorServiceVaadin doctorServiceVaadin;

    public AllDoctorGrid() {
        final Grid<DoctorInfoDto> doctorInfoDtoGrid = new Grid<DoctorInfoDto>(DoctorInfoDto.class);

        final Button fetchDoctors = new Button("Fetch all doctors",
                e -> doctorInfoDtoGrid.setItems(doctorServiceVaadin.getAllDoctors()));

        final Button goTo = new Button("Go to",
                e -> UI.getCurrent().navigate("doctor-form/" +
                        doctorInfoDtoGrid.getSelectionModel().getSelectedItems().iterator().next().getId())
        );

        fetchDoctors.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        goTo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(fetchDoctors, goTo, doctorInfoDtoGrid);
    }
}
