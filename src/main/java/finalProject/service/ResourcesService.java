package finalProject.service;

import finalProject.domain.EducationResource;
import finalProject.repository.ResourcesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ResourcesService {
    private static final Logger logger= LoggerFactory.getLogger(ResourcesService.class);
    @Value("${upload.path}")
    private String uploadPath;
    private final ResourcesRepo resourcesRepo;
@Autowired
    public ResourcesService(ResourcesRepo resourcesRepo) {
        this.resourcesRepo = resourcesRepo;
    }

    public void saveResource(EducationResource resource, MultipartFile resourceFile) throws IOException{
    String filePath=saveFile(resourceFile);
    EducationResource educationResource=new EducationResource();
     try{
         educationResource.setTitle(resource.getTitle());
         educationResource.setCategory(resource.getCategory());
         educationResource.setUploadDate(LocalDate.now());
         educationResource.setUploadPath(filePath);
         resourcesRepo.save(educationResource);
     }
     catch (Exception ex){
         logger.error("An error occurred while saving the request", ex);
     }

    }
    private String saveFile(MultipartFile file) throws IOException {
        String fileName= UUID.randomUUID() +file.getOriginalFilename();
        Path filesDirectory= Paths.get(uploadPath);
        if(!Files.exists(filesDirectory)){
            Files.createDirectories(filesDirectory);
        }
        Path filePath=filesDirectory.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public List<EducationResource>allResources(){
    return resourcesRepo.findAll();
    }
    public EducationResource findEducationResource(int id){
      return resourcesRepo.findById(id).orElse(null);
    }
    public boolean deleteResource(int id){
     EducationResource resource=resourcesRepo.findById(id).orElse(null);
     if(resource!=null){
         resourcesRepo.delete(resource);
         return true;
     }
     return false;
}
public List<EducationResource>findByCategory(String category){
    return resourcesRepo.findByCategory(category);
}

}
