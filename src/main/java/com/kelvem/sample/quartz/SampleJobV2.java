package com.kelvem.sample.quartz;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("sampleJobV2")
public class SampleJobV2 {

	public void execute() {
		System.out.println(new Date());
		
	}

}
