package crud.springboot.controller;



import crud.springboot.model.Assignment;
import crud.springboot.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    public void init() {
        assignmentService.init();
    }

    @GetMapping("/assignments")
    public String viewAssignments(Model model) {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        return "assignments";
    }

    @GetMapping("/addAssignment")
    public String showAddAssignmentForm() {
        return "add-assignment";
    }

    @GetMapping("/add-assignment")
    public String showAddAssignmentPage() {
        return "add-assignment";
    }
        

    @PostMapping("/uploadAssignment")
    public String uploadAssignment(
            @RequestParam("studentName") String studentName,
            @RequestParam("file") MultipartFile file,
            Model model) {

        try {
            assignmentService.saveAssignment(studentName, file);
            model.addAttribute("message", "Assignment uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to upload assignment: " + e.getMessage());
        }

        return "redirect:/assignments";
    }

    @GetMapping("/assignments/download/{fileName}")
    public ResponseEntity<Resource> downloadAssignment(@PathVariable String fileName) {
        Resource file = assignmentService.getAssignment(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }
}

