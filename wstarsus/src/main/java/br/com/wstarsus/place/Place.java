package br.com.wstarsus.place;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Place {
	private String html_attributions;
	private String next_page_token;
	private List<Places> results;
	
	public String getHtml_attributions() {
		return html_attributions;
	}
	public void setHtml_attributions(String html_attributions) {
		this.html_attributions = html_attributions;
	}
	public String getNext_page_token() {
		return next_page_token;
	}
	public void setNext_page_token(String next_page_token) {
		this.next_page_token = next_page_token;
	}
	public List<Places> getResults() {
		return results;
	}
	public void setResults(List<Places> results) {
		this.results = results;
	}
	
	
}
