package com.example.animetracker.dto;

import com.example.animetracker.model.ApplicationUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(ApplicationUser user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }

}
