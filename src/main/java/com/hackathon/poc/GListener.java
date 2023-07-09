package com.hackathon.poc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GListener {

	@PostMapping(path = "/api/receive")
	public void receivePushNotfcns(@RequestBody String s) {
		System.out.println("---------" + s);
	}

	@GetMapping("/")
	public String hello() {
		return "hello world!";
	}
}
