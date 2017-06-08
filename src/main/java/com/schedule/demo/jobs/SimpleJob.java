package com.schedule.demo.jobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements org.quartz.Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String appUrl = jobDataMap.getString("appUrl");
		boolean appCallStatus = false;
		String resCode = "";
		String successfulCode = jobDataMap.getString("successfulCode");
		try {
			resCode = submitGetRequest(successfulCode, appUrl);
			if (successfulCode != null && successfulCode.equals(resCode)) {
				appCallStatus = true;
			} else {
				appCallStatus = false;
			}
		} catch (Exception e) {
			appCallStatus = false;
			e.printStackTrace();
		} finally {
			// all db to update job running status
			System.out.println("Job running:" + appCallStatus);

		}

	}

	private String submitGetRequest(String expectedResponse, String appUrl) throws Exception {
		String resBody = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(appUrl);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
			System.out.println("Calling:" + appUrl + "," + response1.getStatusLine());
			resBody = getResponseBodyAsString(response1);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				response1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resBody;
	}

	private boolean submitPostRequest(String expectedResponse, String appUrl) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(appUrl);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} finally {
			response2.close();
		}
		return true;
	}

	public static String getResponseBodyAsString(HttpResponse response) throws Exception {
		StringBuilder sb = new StringBuilder();
		HttpEntity httpEntity = response.getEntity();
		if (httpEntity != null) {
			httpEntity = new BufferedHttpEntity(httpEntity);
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			is.close();
		}
		return sb.toString();
	}
}
