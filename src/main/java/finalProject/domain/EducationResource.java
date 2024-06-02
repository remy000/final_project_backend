package finalProject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class EducationResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resourceId;
    private String title;
    private String category;
    private LocalDate uploadDate;
    private String uploadPath;

    public EducationResource() {
    }

    public EducationResource(int resourceId, String title, String category, LocalDate uploadDate, String uploadPath) {
        this.resourceId = resourceId;
        this.title = title;
        this.category = category;
        this.uploadDate = uploadDate;
        this.uploadPath = uploadPath;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
