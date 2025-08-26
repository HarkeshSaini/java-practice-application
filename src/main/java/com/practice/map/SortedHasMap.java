package com.practice.map;

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
@RequestMapping(path = "/v1/hasMap")
public class SortedHasMap {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SortedHasMap.class);
	
	@PostMapping(path = "/sortedHashMap")
	private ResponseEntity<APIResponse<Map<String, Object>>> sortedHashMap(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for sorted Sorted HashMap:");
		return sortedHashMapLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, Object>>> sortedHashMapLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			HashMap<String, Object> arrayRes=new HashMap<String, Object>();
			if(apiRequest.isStatus()) {
				arrayRes=HashMapDetail.hashMapInteger()
						.entrySet().stream().sorted(Map.Entry.comparingByValue())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(q,qq)->qq, LinkedHashMap::new));
				message="Custom Code";
				APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.OK, "success", true,message, arrayRes);
				return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
			}
			arrayRes.put("sortedMap", null);
			message=" Status true is Required:";
			APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.OK, "success", true,message, arrayRes);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		} catch (Exception e) {
			Map<String, Object> errorRes = Map.of("error",e.getLocalizedMessage());
			APIResponse<Map<String,Object>> apiResponse = new APIResponse<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", errorRes);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}

