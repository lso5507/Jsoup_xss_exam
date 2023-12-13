package com.example.jsoup_xss;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class JsoupXssTest {
	@Test
	public void JsoupXssTest(){
		String html = "<div>   <p>테스트</p>   <img src='\"https://www.naver.com\"' onerror='\\''https://www.naver.com'\\''/>    <script> alert('\\''test'\\'')</script> </div>'";

		String content = Jsoup.clean(html, "https://www.naver",Safelist.relaxed().preserveRelativeLinks(true));
		String expectHtml ="<div>\n"
			+ " <p>테스트</p><img src=\"&quot;https://www.naver.com&quot;\">\n"
			+ "</div>'";
		Assertions.assertEquals(content,expectHtml);
	}
	@Test
	public void JsoupXssBasicTest(){
		String html = "<div>   <p>테스트</p>   <img src='\"https://www.naver.com\"' onerror='\\''https://www.naver.com'\\''/>    <script> alert('\\''test'\\'')</script> </div>'";

		String content = Jsoup.clean(html, Safelist.relaxed());
		String expectHtml ="<div>\n"
			+ " <p>테스트</p><img>\n"
			+ "</div>'";
		Assertions.assertEquals(content,expectHtml);
	}
	@Test
	public void JsoupXssScriptTest(){
		String html = "<script>alert('test')</script>";

		String content = Jsoup.clean(html, Safelist.relaxed());
		Assertions.assertEquals(content,"");
	}
}
