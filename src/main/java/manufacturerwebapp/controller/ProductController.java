package manufacturerwebapp.controller;

import manufacturerwebapp.model.Product;
import manufacturerwebapp.service.ManufacturerService;
import manufacturerwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ManufacturerService manufacturerService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listProducts(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.list());
        model.addAttribute("manufacturers", manufacturerService.list());
        return "products";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String addProduct(@ModelAttribute ("product")Product product){

        System.out.println("!!!!!!!!!!" + product);
        if(product.getId() == null){
            this.productService.save(product);
        }else {
            this.productService.update(product);
        }

        return "redirect:/products";
    }

    @RequestMapping("/products/edit/{id}")
    @Secured("ROLE_ADMIN")
    public String editManufacturer(@PathVariable("id") UUID id, Model model){
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("products", productService.list());
        model.addAttribute("manufacturers", manufacturerService.list());
        return "products";
    }

    @RequestMapping("/products/remove/{id}")
    @Secured("ROLE_ADMIN")
    public String removeManufacturer(@PathVariable("id") UUID id){
        productService.remove(id);
        return "redirect:/products";
    }
}
