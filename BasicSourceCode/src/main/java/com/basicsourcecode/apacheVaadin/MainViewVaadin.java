package com.basicsourcecode.apacheVaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("home")
public class MainViewVaadin extends VerticalLayout {
    public MainViewVaadin() {
        Button button = new Button("Click me", e -> Notification.show("Button clicked!"));
        add(button);
        add(new H1("salam"));
    }

}
