package com.drmed.vaadin.about;

import com.drmed.vaadin.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./styles/views/about/about.css")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        String text = "Dr Med + Trello synchronization and Api Medic diagnosis. " +
                "For more info go to: https://github.com/zagPatryk/DrMed" ;

        String attention = "Attention! This is not a fully completed project. It is being developed all the time. " +
                "The current frontend is only demonstration and does not represent the entire project but a fraction of it.";

        add(new H1(text));
        add(new H2(attention));
    }
}
