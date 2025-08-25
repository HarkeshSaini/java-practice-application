package com.practice.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
@RequestMapping(path = "/v1/array")
public class DuplicateElement {

	private final static Logger LOGGER = LoggerFactory.getLogger(DuplicateElement.class);

	@PostMapping(path = "/duplicateElement")
	private ResponseEntity<APIResponse<Map<String, String>>> duplicateElement(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for duplicate Element:");
		return duplicateElementLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, String>>> duplicateElementLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			Map<String, String> arrayRes=new HashMap<String, String>();
			int[] array1 = ArrayDetail.array1;
			int[] arrays1=new int[array1.length];
			if (apiRequest.isStatus()) {
				for (int i = 0; i < array1.length; i++) {
					for (int j = 0; j < array1.length; j++) {
						 
					} 
				}
				arrayRes=Map.of("array",Arrays.toString(arrays1));
				message="Custom Code";
			}else {
				HashSet<Integer> hashSet=new HashSet<Integer>();
				HashSet<Integer> duplicate=new HashSet<Integer>();
				for(Integer integer:array1) {
					if(!hashSet.add(integer)) {
						duplicate.add(integer);
					}
				}
				arrayRes = Map.of("array",Arrays.toString(duplicate.toArray()));
				message="pre-define java method";
			}
			APIResponse<Map<String, String>> apiResponse = new APIResponse<Map<String, String>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		}catch (Exception e) {
			Map<String, String> errorRes = Map.of("error",e.getLocalizedMessage());
			APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}
