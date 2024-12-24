package crud.springboot.service;



import crud.springboot.model.Assignment;
import crud.springboot.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AssignmentService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private AssignmentRepository assignmentRepository;

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void saveAssignment(String studentName, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));

            Assignment assignment = new Assignment(studentName, fileName);
            assignmentRepository.save(assignment);
        } catch (IOException e) {
            throw new RuntimeException("Could not save file. Error: " + e.getMessage());
        }
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Resource getAssignment(String fileName) {
        try {
            Path file = root.resolve(fileName);
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

