package com.auction.pro.common.utils;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;

public class SolrServer {
	@Value("${solr.server.url}")
	private static String solrURL;

	public static HttpSolrServer solrServer() throws Exception {
		return new HttpSolrServer(solrURL);
	}
}
