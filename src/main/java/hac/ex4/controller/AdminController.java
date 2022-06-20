package hac.ex4.controller;

import hac.ex4.Ex4Application;
import hac.ex4.model.Product;
import hac.ex4.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("admin/")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final ProductService service;

    @Autowired
    public AdminController(ProductService service) {
        this.service = service;
    }

    @GetMapping("login")
    public ModelAndView index(Model model) {
        return new ModelAndView("admin_login");
    }

    @PostMapping("login")
    public ModelAndView adminLogin(Model model,
                                   @RequestParam String username,
                                   @RequestParam String password) {
        // verify pass ---
        if (username.equals("admin") && password.equals("admin")) {
            return new ModelAndView("redirect:home");
        } else {
            model.addAttribute("message", "Invalid username or password please try again");
            return new ModelAndView("admin_login");
        }
    }

    @GetMapping("home")
    public ModelAndView home(@RequestParam(required = false) String message) {
        ModelAndView modelAndView = new ModelAndView("admin_home");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("add-product")
    public ModelAndView addProduct(Model model) {
        return new ModelAndView("add_product");
    }

    @PostMapping("add-product")
    public ModelAndView addProductPost(@RequestParam Map<String, String> allParams) {
        String name = allParams.get("name");
        int quantity = Integer.parseInt(allParams.get("quantity"));
        int price = Integer.parseInt(allParams.get("price"));
        int discount = Integer.parseInt(allParams.get("discount"));
        String src = allParams.get("src");

        // crate product and save in db
        Product product = new Product(name, src, quantity, price, discount);
        service.save(product);

//        logger.error(product.toString());

        // create ModelAndView
        ModelAndView modelAndView = new ModelAndView("add_product");
        modelAndView.addObject("message", "Product added successfully.");
        return modelAndView;
    }


    @GetMapping("update-product")
    public ModelAndView updateProduct(Model model) {
        // create ModelAndView
        ModelAndView modelAndView = new ModelAndView("update_product");
        modelAndView.addObject("message", null);
        return modelAndView;
    }

    @PostMapping("update-product")
    public ModelAndView updateProductPost(@RequestParam Map<String, String> allParams) {
        long id = Long.parseLong(allParams.get("id"));
        String name = allParams.get("name");
        int quantity = Integer.parseInt(allParams.get("quantity"));
        int price = Integer.parseInt(allParams.get("price"));
        int discount = Integer.parseInt(allParams.get("discount"));
        String src = allParams.get("src");

        try {
            service.findById(id);
        } catch (Exception e) {
            // create ModelAndView
            ModelAndView modelAndView = new ModelAndView("update_product");
            modelAndView.addObject("message",
                    "Product not found, please enter valid information");
            return modelAndView;
        }

        // crate product and save in db
        Product product = new Product(id, name, src, quantity, price, discount);
        service.save(product);

        // create ModelAndView
        ModelAndView modelAndView = new ModelAndView("update_product");
        modelAndView.addObject("message", "Product updated successfully.");
        return modelAndView;
    }

    @GetMapping("remove-product")
    public ModelAndView removeProduct(Model model) {
        // create ModelAndView
        ModelAndView modelAndView = new ModelAndView("rev_product");
        modelAndView.addObject("message", null);
        return modelAndView;
    }

    @PostMapping("remove-product")
    public ModelAndView removeProductPost(@RequestParam Map<String, String> allParams) {
        try {
            long id = Long.parseLong(allParams.get("id"));
            Product product = service.findById(id);
            service.delete(product);

            // create ModelAndView
            ModelAndView modelAndView = new ModelAndView("rev_product");
            modelAndView.addObject("message", "Product removed successfully.");
            return modelAndView;
        } catch (Exception e) {

            // create ModelAndView
            ModelAndView modelAndView = new ModelAndView("rev_product");
            modelAndView.addObject("message",
                    "Product not found, unable to delete product." +
                            " Please enter a valid information!");
            return modelAndView;
        }
    }

    @GetMapping("view-product")
    public ModelAndView viewProduct(Model model) {
        // create ModelAndView
        ModelAndView modelAndView = new ModelAndView("view_product");
        modelAndView.addObject("products", service.findAll());
        return modelAndView;
    }

}
