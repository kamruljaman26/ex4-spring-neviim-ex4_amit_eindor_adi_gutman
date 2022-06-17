package hac.ex4.service;

import hac.ex4.model.Product;
import hac.ex4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public Product save(Product product){
        return repository.save(product);
    }

    public Product update(Product product){
        return repository.save(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }

    public List<Product> findAll(){
        List<Product> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public Product findById(long id){
        return repository.findById(id).get();
    }
}
