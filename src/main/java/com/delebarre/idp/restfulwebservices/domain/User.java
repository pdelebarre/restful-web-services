package com.delebarre.idp.restfulwebservices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ApiModel(description = "All details about the user.")
public class User {
    @Id
    private Integer id;

    @Size(min = 2, message="name should have at least 2 characters")
    @ApiModelProperty(notes="name should have at least 2 characters")
    private String name;

    @Past
    @ApiModelProperty(notes="Birth date should be in the past")
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    protected User(){

    }


    public List<Post> getPosts() {
        return this.posts;
    }

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
