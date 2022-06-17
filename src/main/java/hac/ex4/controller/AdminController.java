package hac.ex4.controller;

import hac.ex4.Ex4Application;
import hac.ex4.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    private ProductService service;

    @Autowired
    public AdminController(ProductService service) {
        this.service = service;
    }

    @GetMapping("login")
    public String index(Model model) {

        logger.error("GGG");
        logger.debug("KKK");
        model.addAttribute("demo", "admin login");
        model.addAttribute("productList", service.findAll());

        return "admin_login";
    }

    @PostMapping("login")
    public ModelAndView adminLogin(Model model) {
        logger.error((String) model.getAttribute("username"));
        return new ModelAndView("redirect:home");
    }

    @GetMapping("home")
    public ModelAndView home(Model model) {
        return new ModelAndView("admin_home");
    }
}
