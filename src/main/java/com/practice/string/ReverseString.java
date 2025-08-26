package com.practice.string;

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
@RequestMapping(path = "/v1/string")
public class ReverseString {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ReverseString.class);
	
	@PostMapping(path = "/reverseString")
	private ResponseEntity<APIResponse<Map<String, Object>>> reverseString(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for count String Word:");
		return reverseStringLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, Object>>> reverseStringLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			Map<String, Object> arrayRes=new HashMap<>();
			if(apiRequest.isStatus()) {
				String str=StringDetail.strValue;
				String rev=new String();
				for(int i=str.length()-1; i>=0; --i) {
					rev+=str.charAt(i);
				}
				arrayRes.put("str", rev);
				message="Custom Code: {}," +str;
				APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.OK, "success", true,message, arrayRes);
				return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
			}
			arrayRes.put("sortedMap", null);
			message=" Status true is Required:";
			APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		} catch (Exception e) {
			Map<String, Object> errorRes = Map.of("error",e.getMessage());
			APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}

