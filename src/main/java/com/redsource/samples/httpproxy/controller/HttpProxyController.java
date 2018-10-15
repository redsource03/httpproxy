package com.redsource.samples.httpproxy.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redsource.samples.httpproxy.model.InputModel;
import com.redsource.samples.httpproxy.model.ResponseModel;
import com.redsource.samples.httpproxy.wrapper.EndPointEnum;
import com.redsource.samples.httpproxy.wrapper.RequestBuilder;

@RestController
public class HttpProxyController {
	@Autowired
	RequestBuilder reqBuild;
	
	@PostMapping("/input")
	@ResponseBody
	public ResponseModel test(@RequestBody InputModel input) {
		ResponseModel model = new ResponseModel();
		model.setMessage("SUCCESSS!!");
		return model;
	}
	@RequestMapping("/send")
	@ResponseBody
	public ResponseModel test2() {
		ResponseModel model = new ResponseModel();
		InputModel inputModel =  new InputModel();
		inputModel.setFirstName("Paul Vincent");
		inputModel.setMiddleName("Razon");
		inputModel.setLastName("Cabunilas");
		try {
			model.setMessage(reqBuild.createPostRequest("http://localhost:8080"+EndPointEnum.TEST.getEndpoint(), inputModel));
		} catch (UnsupportedCharsetException | JsonProcessingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}
