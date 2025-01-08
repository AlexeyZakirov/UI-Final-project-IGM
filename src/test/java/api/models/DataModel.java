package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataModel {

	private String nickname;

	@JsonProperty("last_name")
	private Object lastName;

	private Integer id;

	@JsonProperty("first_name")
	private Object firstName;

	private String email;
}