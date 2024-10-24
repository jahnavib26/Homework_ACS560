package com.acs560.restaurantsales.restaurant_sales.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.acs560.restaurantsales.restaurant_sales.security.SecurityService;
import com.acs560.restaurantsales.restaurant_sales.views.sales.SalesView;
import com.acs560.restaurantsales.restaurant_sales.views.ItemDetails.ItemDetailsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main layout for the application.
 */
public class MainLayout extends AppLayout {

	private static final long serialVersionUID = -5291741451913578403L;
	
    private final SecurityService securityService;

	/**
	 * Constructor
	 * @param securityService The security service to manage user authentication and logout
	 */
	@Autowired
	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    /**
     * Create the header with a logout button displaying the username.
     */
    private void createHeader() {
        H1 logo = new H1("Restaurant Sales");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM);

        String username = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + username, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 
    }

    /**
     * Create the drawer for navigation between views.
     */
    private void createDrawer() {
    	RouterLink salesLink = new RouterLink("Sales", SalesView.class);
    	salesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink itemDetailsLink = new RouterLink("ItemDetails", ItemDetailsView.class);
        
        addToDrawer(new VerticalLayout(salesLink, itemDetailsLink));
    }
}
