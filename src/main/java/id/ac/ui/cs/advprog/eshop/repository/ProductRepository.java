package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends AbstractItemRepository<Product> {
    @Override
    public Product update(String id, Product updatedProduct) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                Product product = items.get(i);
                product.setName(updatedProduct.getName());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }
        return null;
    }
}