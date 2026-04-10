package studentmanagement;
import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String branch;
    private String skills;
    private double cgpa;
    private String placementStatus;
    private String company;

    public Student(int id, String name, String branch, String skills,
                   double cgpa, String placementStatus, String company) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.skills = skills;
        this.cgpa = cgpa;
        this.placementStatus = placementStatus;
        this.company = company;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public String getSkills() { return skills; }
    public double getCgpa() { return cgpa; }
    public String getPlacementStatus() { return placementStatus; }
    public String getCompany() { return company; }

    public void setName(String name) { this.name = name; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public void setPlacementStatus(String placementStatus) { this.placementStatus = placementStatus; }
    public void setCompany(String company) { this.company = company; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + branch + " | " + skills +
               " | " + cgpa + " | " + placementStatus + " | " + company;
    }
}