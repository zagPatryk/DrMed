package com.drmed.vaadin.doctor;

import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.vaadin.main.MainView;
import com.drmed.vaadin.service.DoctorServiceVaadin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "all-doctors", layout = MainView.class)
@PageTitle("All doctors")
@CssImport("./styles/views/doctor/doctor-view.css")
public class AllDoctorGrid extends Div {
    @Autowired
    private DoctorServiceVaadin doctorServiceVaadin;

    private Button fetchDoctors = new Button("Fetch all doctors");
    private Button goTo = new Button("Go to");

    public AllDoctorGrid() {
        final Grid<DoctorInfoDto> doctorInfoDtoGrid = new Grid<DoctorInfoDto>(DoctorInfoDto.class);

        fetchDoctors.addClickListener(
                e -> doctorInfoDtoGrid.setItems(doctorServiceVaadin.getAllDoctors()));

        goTo.addClickListener(
                e -> UI.getCurrent().navigate("doctor-form/" +
                doctorInfoDtoGrid.getSelectionModel().getSelectedItems().iterator().next().getId()));

        add(createTitle());
        add(createButtonLayout());
        add(fetchDoctors, goTo, doctorInfoDtoGrid);
    }

    private Component createTitle() {
        return new H3("All doctors");
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        fetchDoctors.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(fetchDoctors);
        buttonLayout.setSpacing(true);
        buttonLayout.add(goTo);
        return buttonLayout;
    }
}
