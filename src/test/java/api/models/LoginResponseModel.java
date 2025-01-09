package api.models;

import lombok.Data;

@Data
public class LoginResponseModel {

    private DataModel data;

    private String status;
}