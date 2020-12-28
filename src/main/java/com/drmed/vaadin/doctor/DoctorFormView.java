package com.drmed.vaadin.doctor;

import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.vaadin.main.MainView;
import com.drmed.vaadin.service.DoctorServiceVaadin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "doctor-form", layout = MainView.class)
@PageTitle("Doctor Form")
@CssImport("./styles/views/personform/person-form-view.css")
public class DoctorFormView extends Div implements HasUrlParameter<String> {
    @Autowired
    private DoctorServiceVaadin doctorServiceVaadin;

    private TextField code = new TextField("Doctor code");
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
//    private ComboBox<ActivityStatus> activityStatus = new ComboBox<>("Activity Status");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private DoctorDto doctorDto;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if(parameter == null) {
            doctorDto = new DoctorDto();
        } else {
            doctorDto = doctorServiceVaadin.getDoctorById(new Long(parameter));
            startingForm();
        }
    }

    private Binder<DoctorDto> binder = new Binder(DoctorDto.class);

    public DoctorFormView() {
        setId("doctor-form");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        startingForm();

        cancel.addClickListener(e -> startingForm());
        save.addClickListener(e -> {
            doctorServiceVaadin.updateDoctor(binder.getBean());
            Notification.show("Doctor details stored.");
            startingForm();
        });
    }

    private void startingForm() {
        if (doctorDto != null) {
            binder.setBean(doctorDto);
            code.setValue(doctorDto.getCode() == null ? "" : doctorDto.getCode());
            firstName.setValue(doctorDto.getFirstName() == null ? "" : doctorDto.getFirstName());
            lastName.setValue(doctorDto.getLastName() == null ? "" : doctorDto.getLastName());
            email.setValue(doctorDto.getEmail() == null ? "" : doctorDto.getEmail());
        } else {
            binder.setBean(new DoctorDto());
        }
    }

    private Component createTitle() {
        return new H3("Doctor information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
//        formLayout.add(code, firstName, lastName, email, activityStatus);
        formLayout.add(code, firstName, lastName, email);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
