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
public class MergeTwoArray {

	private final static Logger LOGGER = LoggerFactory.getLogger(MergeTwoArray.class);

	@PostMapping(path = "/mergeTwoArray")
	private ResponseEntity<APIResponse<Map<String, String>>> mergeTwoArrayUsingLoop(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for merge two array:");
		return mergeTwoArrayUsingLoopMain(apiRequest);
	}

	private ResponseEntity<APIResponse<Map<String, String>>> mergeTwoArrayUsingLoopMain(APIRequest apiRequest) {
		try {
			String message=new String();
			int[] array1 = ArrayDetail.array1;
			int[] array2 = ArrayDetail.array2;
			Map<String, String> mapResp=new HashMap<String, String>();
			int[] arrys = new int[array1.length+array2.length];
			if (apiRequest.isStatus()) {
				for (int i = 0; i < array1.length; i++) {
					for (int j = 0; j < array2.length; j++) {
						arrys[i]=array1[i];
						arrys[j+i+1]=array2[j];
					}
				}
				mapResp=Map.of("array",Arrays.toString(arrys));
				message="Custom Code";
			}else {
				System.arraycopy(array1, 0, arrys, 0, array1.length);
				System.arraycopy(array2, 0, arrys, array1.length, array2.length);
				mapResp=Map.of("array",Arrays.toString(arrys));
				message="pre-define java method";
			}
			APIResponse<Map<String, String>> apiResponse = new APIResponse<Map<String, String>>(HttpStatus.OK, "success", true,message, mapResp);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			APIResponse<Map<String, String>> apiResponse = new APIResponse<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}
