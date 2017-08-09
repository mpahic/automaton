package com.cloudcog.automaton.admin.view.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

import com.cloudcog.automaton.admin.data.entity.User;
import com.cloudcog.automaton.admin.data.service.UserService;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class UserAdminDataProvider extends FilterablePageableDataProvider<User, Object> {
	private static final long serialVersionUID = -1797943914132606436L;
	private final UserService userService;

	@Autowired
	public UserAdminDataProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Page<User> fetchFromBackEnd(Query<User, Object> query, Pageable pageable) {
		return userService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<User, Object> query) {
		return (int) userService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("username", SortDirection.ASCENDING));
		return sortOrders;
	}

}