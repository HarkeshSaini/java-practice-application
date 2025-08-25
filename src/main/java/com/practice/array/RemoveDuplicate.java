package com.practice.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class RemoveDuplicate {

	private final static Logger LOGGER = LoggerFactory.getLogger(RemoveDuplicate.class);

	@PostMapping(path = "/removeDuplicate")
	private ResponseEntity<APIResponse<Map<String, String>>> removeDuplicate(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for duplicate Element:");
		return removeDuplicateLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, String>>> removeDuplicateLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			Map<String, String> arrayRes=new HashMap<String, String>();
			int[] array1 = ArrayDetail.array1;
			if (apiRequest.isStatus()) {
				Set<Integer> hashSet=new HashSet<Integer>();
				for(Integer integer:array1) {
					hashSet.add(integer);
				}
				arrayRes = Map.of("array",Arrays.toString(hashSet.toArray()));
				message="Custom Code";
			}else {
				var array=Arrays.stream(array1).boxed().distinct().toArray();
				arrayRes = Map.of("array",Arrays.toString(array));
				message="pre-define java method";
			}
			APIResponse<Map<String, String>> apiResponse = new APIResponse<Map<String, String>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			APIResponse<Map<String, String>> apiResponse = new APIResponse<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}
