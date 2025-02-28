package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.AbstractItem;
import id.ac.ui.cs.advprog.eshop.service.ItemService;
import org.springframework.ui.Model;

/**
 * Base controller for item-specific controllers that handle CRUD operations.
 *
 * @param <T> The type of item this controller manages
 */
public abstract class ItemController<T extends AbstractItem> {
    protected final ItemService<T> service;

    protected ItemController(ItemService<T> service) {
        this.service = service;
    }

    protected abstract String getListView();
    protected abstract String getCreateView();
    protected abstract String getEditView();
    protected abstract String getRedirectToList();
    protected abstract String getItemAttributeName();
    protected abstract String getItemsAttributeName();
}