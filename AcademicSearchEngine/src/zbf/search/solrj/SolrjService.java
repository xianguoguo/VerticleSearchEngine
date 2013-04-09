package zbf.search.solrj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import zbf.search.util.StdOutUtil;
import zbf.search.util.StringUtil;

public class SolrjService {
	
	public ArrayList<String> getFieldList(String name) {
		ArrayList<String> fieldlist = new ArrayList<String>();
		SolrjClient newclient = new SolrjClient(0);
		SolrServer server = newclient.getSolrServer();
		SolrQuery query = new SolrQuery();
		
		query.setQuery(StringUtil.transformQuery("name", name));
		QueryResponse rsp;
		try {
			rsp = server.query(query);
			SolrDocumentList docs = rsp.getResults();
			Iterator<SolrDocument> it = docs.iterator();
			while (it.hasNext()) {
				SolrDocument resultDoc = it.next();
				String tmp = (String)resultDoc.getFieldValue("field");
				String[] tmps = tmp.split(", ");
				for (String s : tmps) {
					if (!fieldlist.contains(s)) {
						fieldlist.add(s);
					}
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return fieldlist;
	}
	
	public ArrayList<String> getPlaceList(String name) {
		ArrayList<String> placelist = new ArrayList<String>();
		SolrjClient newclient = new SolrjClient(0);
		SolrServer server = newclient.getSolrServer();
		SolrQuery query = new SolrQuery();
		
		query.setQuery(StringUtil.transformQuery("name", name));
		QueryResponse rsp;
		try {
			rsp = server.query(query);
			SolrDocumentList docs = rsp.getResults();
			Iterator<SolrDocument> it = docs.iterator();
			while (it.hasNext()) {
				SolrDocument resultDoc = it.next();
				String s = (String)resultDoc.getFieldValue("workplace");

				if (!placelist.contains(s)) {
					placelist.add(s);
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return placelist;
	}
}
