package com.practice.array;

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
	private ResponseEntity<APIResponse<int[]>> sortedArray(@RequestBody APIRequest apiRequest) {
		LOGGER.info("call api for sorted Array:");
		return sortedArrayLogic(apiRequest);
	}

	private ResponseEntity<APIResponse<int[]>> sortedArrayLogic(APIRequest apiRequest) {
		try {
			String message=new String();
			int[] array1 = ArrayDetail.array1;
			int[] array2 = ArrayDetail.array2;
			int[] arrys = new int[array1.length+array2.length];
			if (apiRequest.isStatus()) {
				for (int i = 0; i < array1.length; i++) {
					for (int j = 0; j < array2.length; j++) {
						arrys[i]=array1[i];
						arrys[j+i+1]=array2[j];
					}
				}
				message="Custom Code";
			}else {
				System.arraycopy(array1, 0, arrys, 0, array1.length);
				System.arraycopy(array2, 0, arrys, array1.length, array2.length);
				message="pre-define java method";
			}
			APIResponse<int[]> apiResponse = new APIResponse<int[]>(HttpStatus.OK, "success", true,message, arrys);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			APIResponse<int[]> apiResponse = new APIResponse<int[]>(HttpStatus.INTERNAL_SERVER_ERROR, "failure", false, "error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

}
