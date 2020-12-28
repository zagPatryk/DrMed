package com.drmed.vaadin.about;

import com.drmed.vaadin.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        String text = "Dr Med + Trello synchronization and Api Medic diagnosis. " +
                "For more info go to: https://github.com/zagPatryk/DrMed" ;
        add(new Text(text));
    }
}
