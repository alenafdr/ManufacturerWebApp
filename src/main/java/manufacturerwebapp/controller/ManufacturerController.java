package manufacturerwebapp.controller;

import manufacturerwebapp.dao.ManufacturerDao;
import manufacturerwebapp.model.Manufacturer;
import manufacturerwebapp.service.ManufacturerService;
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
public class ManufacturerController {

    @Autowired(required = true)
    ManufacturerService manufacturerService;

    @Autowired
    ManufacturerDao manufacturerDao;

    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
    public String listManufacturers(Model model){
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("manufacturers", manufacturerService.list());
        return "manufacturers";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manufacturers/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("manufacturer")Manufacturer manufacturer){
        if(manufacturer.getId() == null){
            this.manufacturerService.save(manufacturer);
        }else {
            this.manufacturerService.update(manufacturer);
        }
        return "redirect:/manufacturers";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/manufacturers/edit/{id}")
    public String editManufacturer(@PathVariable("id") UUID id, Model model){
        model.addAttribute("manufacturer", manufacturerService.getById(id));
        model.addAttribute("manufacturers", manufacturerService.list());
        return "manufacturers";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/manufacturers/remove/{id}")
    public String removeManufacturer(@PathVariable("id") UUID id){
        manufacturerService.remove(id);
        return "redirect:/manufacturers";
    }
}
