package com.demoapp.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteUserResponseBody {


	private Integer httpStatusCode;
	private String message;


}
