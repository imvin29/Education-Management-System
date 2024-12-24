package crud.springboot.controller;


import crud.springboot.model.Resource;
import crud.springboot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resources")
    public String viewResourcesPage(Model model) {
        model.addAttribute("resources", resourceService.getAllResources());
        return "resources";
    }
    @GetMapping("/addResource")
    public String showAddResourceForm(){
        return "addResource";
    }


    @PostMapping("/saveResource")
    public String saveResource(Resource resource) {
        resourceService.saveResource(resource);
        return "redirect:/resources";
    }
}

