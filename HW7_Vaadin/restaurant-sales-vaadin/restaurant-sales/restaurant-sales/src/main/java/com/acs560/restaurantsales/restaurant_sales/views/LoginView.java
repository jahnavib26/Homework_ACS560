//package com.acs560.restaurantsales.restaurant_sales.views;
//
//import com.vaadin.flow.component.html.H1;
//import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.component.login.LoginForm;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.BeforeEnterObserver;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Route(value="login") 
//@PageTitle("Login | Restaurant Sales")
//@AnonymousAllowed
//public class LoginView extends VerticalLayout implements BeforeEnterObserver {
//
//    private static final long serialVersionUID = 574154935938093394L;
//    private final LoginForm login = new LoginForm(); 
//    private Map<String, String> users = new HashMap<>();
//
//    public LoginView(){
//    	System.out.print("Sumanth Here in login view");
//        addClassName("login-view");
//        setSizeFull(); 
//        setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);
//
//        // Set up hardcoded users for login
//        users.put("user", "userpass");
//        users.put("admin", "adminpass");
//
//        login.setAction("login");
//        login.addLoginListener(e -> login(e.getUsername(), e.getPassword()));
//        
//        add(new H1("Restaurant Sales"));
//        add(new Span("Username:user, Password:userpass"));
//        add(new Span("Username:admin, Password:adminpass"));
//        add(login);
//    }
//
//    private void login(String username, String password) {
//        // Check if the credentials are valid
//    	System.out.print("Sumanth Here in login view");
//        if (users.containsKey(username) && users.get(username).equals(password)) {
//            // Redirect to main view or dashboard after successful login
//            // Use UI.getCurrent().navigate(MainView.class); // Uncomment this line and create a MainView
//        } else {
//            // Show error
//            login.setError(true);
//        }
//    }
//
//    @Override
//    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
//            login.setError(true);
//        }
//    }
//}


package com.acs560.restaurantsales.restaurant_sales.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login") 
@PageTitle("Login | Restaurant Sales")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm login = new LoginForm(); 

	public LoginView(){
		addClassName("login-view");
		setSizeFull(); 
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");

		add(new H1("Restaurant Sales"));
		
		//Displaying these as helper...don't do this in prod
//		add(new Span("Username: user, Password: userpass"));
		add(new Span("Username: user, Password: password"));
		add(login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation()  
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
	}
}