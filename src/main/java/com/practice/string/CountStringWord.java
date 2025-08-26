package com.practice.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
public class CountStringWord {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CountStringWord.class);
	
	@PostMapping(path = "/countStringWord")
	private ResponseEntity<APIResponse<Map<String, Integer>>> countStringWord(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for count String Word:");
		return countStringWordLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, Integer>>> countStringWordLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			Map<String, Integer> arrayRes=new HashMap<>();
			if(apiRequest.isStatus()) {
				String str=StringDetail.strValue.replace(" ","").toLowerCase();
				char[] charArray = str.toCharArray();
				for(Character character:charArray) {
					arrayRes.put(String.valueOf(character), arrayRes.getOrDefault(character,0)+1);
				}
				message="Custom Code: {}," +str;
				APIResponse<Map<String,Integer>> apiResponse = new APIResponse<Map<String,Integer>>(HttpStatus.OK, "success", true,message, arrayRes);
				return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
			}
			arrayRes.put("sortedMap", null);
			message=" Status true is Required:";
			APIResponse<Map<String,Integer>> apiResponse = new APIResponse<Map<String,Integer>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		} catch (Exception e) {
			Map<String, Integer> errorRes = Map.of("error",404);
			APIResponse<Map<String,Integer>> apiResponse = new APIResponse<Map<String,Integer>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}

