package com.drmed.vaadin.doctor;

import com.drmed.base.doctor.dto.NewDoctorDto;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "new-doctor-form", layout = MainView.class)
@PageTitle("Doctor Form")
@CssImport("./styles/views/doctor/doctor-view.css")
public class NewDoctorFormView extends Div {
    @Autowired
    private DoctorServiceVaadin doctorServiceVaadin;

    private TextField code = new TextField("Doctor code");
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<NewDoctorDto> binder = new Binder(NewDoctorDto.class);

    public NewDoctorFormView() {
        setId("person-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            doctorServiceVaadin.postNewDoctor(binder.getBean());
            Notification.show("New doctor created");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new NewDoctorDto());
    }

    private Component createTitle() {
        return new H3("Doctor information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
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
