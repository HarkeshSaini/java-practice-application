package com.practice.design;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.request.APIRequest;
import com.practice.response.APIResponse;

@RestController
@RequestMapping(path = "/v1/design/pattern")
public class CallApiDesignPattern {

	private final static Logger LOGGER = LoggerFactory.getLogger(CallApiDesignPattern.class);

	@PostMapping(path = "/singleTon")
	private ResponseEntity<APIResponse<Map<String, String>>> singleTon(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for sorted Array:");
		return singleTonLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, String>>> singleTonLogic(APIRequest apiRequest) {
		try {
			Map<String, String> singleTon=new HashMap<String, String>();
			String message=new String();
			if(apiRequest.isStatus()) {
				SingleTonDesignPattern designPattern = SingleTonDesignPattern.singleTonDesignPattern();
				singleTon.put("pattrenCode", String.valueOf(designPattern.hashCode()));
				APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.OK, "success", true,message, singleTon);
				return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
			}
			Map<String, String> errorRes = Map.of("warning","status true reuqired!.");
			APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.BAD_REQUEST, "failure", false,"status fail", errorRes);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		} catch (Exception e) {
			Map<String, String> errorRes = Map.of("error",e.getLocalizedMessage());
			APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}
}
