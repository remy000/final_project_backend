package finalProject.controller;

import finalProject.domain.EducationResource;
import finalProject.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourcesController {
    private final ResourcesService resourcesService;
@Autowired
    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }
@PostMapping("/saveResource")
@PreAuthorize("hasAnyAuthority('healthcare','admin')")
    public ResponseEntity<?>saveResource(@ModelAttribute EducationResource resource, @RequestParam("file") MultipartFile file){
     try {
         resourcesService.saveResource(resource,file);
         return new ResponseEntity<>("resources uploaded",HttpStatus.OK);

     }
     catch (Exception ex){
         return new ResponseEntity<>("fail to upload resource" +ex.getMessage(), HttpStatus.BAD_REQUEST);
     }
    }
    @GetMapping("/allResources")
    @PreAuthorize("hasAnyAuthority('healthcare','admin')")
    public ResponseEntity<?>allResources(){
        List<EducationResource>resourceList=resourcesService.allResources();
        if(resourceList!=null){
            List<EducationResource>list=resourceList.stream().peek(resource -> {
                String resourcePath= "http://localhost:8080/files/" +resource.getUploadPath();
                resource.setUploadPath(resourcePath);
            }).toList();
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("fail to fetch resources",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/findResource/{id}")
    @PreAuthorize("hasAnyAuthority('healthcare','admin','patient')")
    public ResponseEntity<?>findResource(@PathVariable ("id") int id){
     EducationResource resource=resourcesService.findEducationResource(id);
     if(resource!=null){
         resource.setUploadPath("http://localhost:8080/files/" +resource.getUploadPath());
         return new ResponseEntity<>(resource,HttpStatus.OK);
     }
     return new ResponseEntity<>("resource not found",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/resourceByCategory/{category}")
    @PreAuthorize("hasAnyAuthority('healthcare','admin','patient')")
    public ResponseEntity<?>findResourceByCategory(@PathVariable("category") String category){
    List<EducationResource>resourceList=resourcesService.findByCategory(category);
        if(resourceList!=null){
            List<EducationResource>list=resourceList.stream().peek(resource -> {
                String resourcePath= "http://192.168.1.154:8080/files/" +resource.getUploadPath();
                resource.setUploadPath(resourcePath);
            }).toList();
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("fail to fetch resources",HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<?>deleteResource(@PathVariable("id") int id){
    boolean isDeleted= resourcesService.deleteResource(id);
    if(isDeleted){
        return new ResponseEntity<>("resource deleted",HttpStatus.OK);
    }
    return new ResponseEntity<>("fail to delete resource",HttpStatus.BAD_REQUEST);
    }

}
