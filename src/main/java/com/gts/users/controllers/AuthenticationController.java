package com.gts.users.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gts.users.model.request.SwaggerLoginRequestModel;

import io.swagger.annotations.ApiOperation;


@RestController
public class AuthenticationController {
	


	@ApiOperation(value="User Login Web Service Endpoint",    notes="${userController.createUser.notes}")
    @PutMapping("/users/login")
	public void theDummyLogin(@RequestBody SwaggerLoginRequestModel swaggerLoginRequestModel )
	{
		throw new IllegalStateException("This method should not be called it is only for documentation purpose");
	}
}


// this is dummy Controller only for Swagger documentation
