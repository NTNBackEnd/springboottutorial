package SpringBootTutorial.springboot.controller;

import SpringBootTutorial.springboot.model.Product;
import SpringBootTutorial.springboot.model.ResponseObject;
import SpringBootTutorial.springboot.repositories.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class controllerProducts {
    //Link: http://localhost:8080/api/v1/Products
    @Autowired
    private repository repositorie;

    @GetMapping
    List<Product> allProducts() {
        return repositorie.findAll();
    }

    //Get detail product
    @GetMapping("/{id}/user")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repositorie.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", foundProduct));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("false", "Cannot find product with id = " + id, "")
            );
        }
    }

//    @GetMapping
//    ResponseEntity<ResponseObject> findById2(@RequestParam(required = false) Long id) {
//        Optional<Product> foundProduct = repositorie.findById(id);
//        if (foundProduct.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query product successfully", foundProduct));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new ResponseObject("false", "Cannot find product with id = " + id, "")
//            );
//        }
//    }

    //insert new Product with POST method
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foundProducts = repositorie.findByname(newProduct.getName().trim());
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully", repositorie.save(newProduct))
        );
    }
    //update, upset = update if found , otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = repositorie.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setYears(newProduct.getYears());
                    product.setUrl(newProduct.getUrl());
                    return repositorie.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repositorie.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updateProduct )
        );
    }
    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists = repositorie.existsById(id);
        if(exists){
            repositorie.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find product to delete","")
        );

    }
}
