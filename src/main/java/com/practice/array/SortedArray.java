package com.practice.array;

import java.util.Arrays;
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
@RequestMapping(path = "/v1/array")
public class SortedArray {

	private final static Logger LOGGER = LoggerFactory.getLogger(SortedArray.class);

	@PostMapping(path = "/sortedArray")
	private ResponseEntity<APIResponse<Map<String, String>>> sortedArray(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for sorted Array:");
		return sortedArrayLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, String>>> sortedArrayLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			Map<String, String> arrayRes=new HashMap<String, String>();
			int[] array1 = ArrayDetail.array1;
			if (apiRequest.isStatus()) {
				for (int i = 0; i < array1.length; i++) {
					for (int j = 0; j < array1.length; j++) {
						if(array1[j]> array1[i]) {
							int temp=array1[i];
							array1[i]=array1[j];
							array1[j]=temp;
						}
					}
				}
				arrayRes=Map.of("array",Arrays.toString(array1));
				message="Custom Code";
			}else {
				arrayRes = Map.of("array",Arrays.toString(Arrays.stream(array1).boxed().toList().stream().sorted().toArray()));
				message="pre-define java method";
			}
			APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		} catch (Exception e) {
			Map<String, String> errorRes = Map.of("error",e.getLocalizedMessage());
			APIResponse<Map<String,String>> apiResponse = new APIResponse<Map<String,String>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}
