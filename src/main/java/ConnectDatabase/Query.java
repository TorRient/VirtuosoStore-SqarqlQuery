package ConnectDatabase;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Query {

	// In tên Event 1998
	public void Query1(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX event:<http://www.example.org/Event/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE {";
	    queryString += "?s rdf:type label:Event .\n";
	    queryString += "?s ?p ?o .\n";
		queryString += " FILTER (?s = event:Event1998 && ?p = label:label)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	// In ra định danh có tuổi bằng 20
	public void Query2(RepositoryConnection connection) {
		String queryString = "PREFIX age:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += "  ?s rdf:type label:Person .\n";
		queryString += "  ?s ?p ?o .\n";
		queryString += " FILTER (?p = age:age && ?o = 20)";
		queryString += "}";
		queryString += " LIMIT 20";
		this.resultQUERY3(queryString, connection);
	}
	// In ra tên của Person1
	public void Query3(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { ?s ?p ?o .\n";
		queryString += " FILTER (?s =person:Person1 && ?p = label:label)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}
	// In ra person2 live in ở đâu?
	public void Query4(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += " ?s rdf:type label:Person .\n";
		queryString += " ?s ?p ?a .\n";
		queryString += " ?a label:label ?o .\n";
		queryString += " FILTER ( ?s = person:Person3 && ?p = re:live_in)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}
	// Sư kiện Vietnam AutoExpo diễn ra tại đâu?
	public void Query5(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?time \n";
	    queryString += "WHERE { \n";
		queryString += " ?event label:label ?s .\n";
		queryString += " ?event ?p ?a .\n";
		queryString += " ?a rdf:type label:Location .\n";
		queryString += " ?a label:label ?o .\n";
		queryString += " FILTER regex(?s, \"Vietnam AutoExpo\") ";
		queryString += " FILTER (?p =re:held_in)";
		queryString += "}";
		queryString += "LIMIT 1";
		this.resultQUERY3(queryString, connection);
	}
	// Event do tổ chức nào chủ trì thời gian nào diễn ra ?
	public void Query6(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o ?t\n";
	    queryString += "WHERE { \n";
	    queryString += " ?event label:label ?s .\n";
		queryString += " ?event ?p ?org .\n";
		queryString += " ?event ?q ?time .\n";
		queryString += " ?org label:label ?o .\n";
		queryString += " ?time label:label ?t .\n";
		queryString += " FILTER regex(?s, \"INTERDYE ASIA\") ";
		queryString += " FILTER (?p =re:held_by && ?q = re:at)";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY4(queryString, connection);
	}
	// Đếm số người có mặt tại sự kiện Vietnam AutoExpo
	public void Query7(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
	    queryString += "WHERE {";
	    queryString += "?s ?p ?o .\n";
		queryString += " FILTER (?p = re:participate)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	// Andie Trapp là cha của ai?
	public void Query8(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += " ?person label:label ?s .\n";
		queryString += " ?person ?p ?per .\n";
		queryString += " ?per label:label ?o .\n";
		queryString += " FILTER regex(?s, \"Claresta Pevie\") ";
		queryString += " FILTER (?p =re:is_dad_of)";
		queryString += "}";
		queryString += " LIMIT 3";
		this.resultQUERY3(queryString, connection);
	}
	// Tìm 10 người đã từng làm việc tại Sony hoặc Nokia
	public void Query9(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += " ?per label:label ?s .\n";
		queryString += " ?per ?p ?org .\n";
		queryString += " ?org label:label ?o .\n";
		queryString += " FILTER (?p = re:manage)" ;
		queryString += " FILTER (?o = \"Sony\"^^<http://www.w3.org/2001/XMLSchema#string> || ?o = \"Nokia\"^^<http://www.w3.org/2001/XMLSchema#string> )";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}
	// Sự kiện Sugarex Vietnam do nhưng ai tài trợ?
	public void Query10(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += " ?event label:label ?s .\n";
		queryString += " ?event ?p ?per .\n";
		queryString += " ?per label:label ?o .\n";
		queryString += " FILTER regex(?s, \"INTERDYE ASIA\") ";
		queryString += " FILTER (?p =re:donated_by)";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}
	// Palmas, Brazil đã xảy ra sự kiện gì? thời gian nào?
	public void Query11(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t \n";
	    queryString += "WHERE { \n";
	    queryString += "  ?location label:label ?s .\n";
		queryString += "  ?location ?p ?event .\n";
		queryString += "  ?event label:label ?o .\n";
		queryString += "  ?event re:at ?time .\n"; 
		queryString += "  ?time rdf:type label:Time .\n";
		queryString += "  ?time label:label ?t .\n";
		queryString += " FILTER regex(?s, \"Palmas, Brazil\") ";
		queryString += " FILTER (?p =re:held)";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY4(queryString, connection);
	}
	// Florentia Muino đã đến thăm những nước nào?
	public void Query12(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += " ?person label:label ?s .\n";
		queryString += " ?person ?p ?country .\n";
		queryString += " ?country label:label ?o .\n";
		queryString += " FILTER regex(?s, \"Florentia Muino\") ";
		queryString += " FILTER (?p =re:visit)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}
	//Đếm số sư kiện xảy ra vào Oct. 27, 2009
	public void Query13(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
	    queryString += "WHERE {";
	    queryString += "?s rdf:type label:Event .\n";
	    queryString += "?b rdf:type label:Time .\n";
	    queryString += "?s ?p ?b .\n";
	    queryString += "?b label:label ?a .\n";
		queryString += " FILTER regex (?a ,\"Sep. 14, 1994\")";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	//In ra những người tham dự sự kiện INTERDYE ASIA nhưng không tham dự Crypto Expo Asia
	public void Query14(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?o \n";
	    queryString += "WHERE { \n";
		queryString += " ?o ?p ?s .\n";
		queryString += " ?s label:label ?a .\n";
		queryString += " FILTER (?p = re:participate)" ;
		queryString += " FILTER regex (?a , \"INTERDYE ASIA\")";
		queryString += " FILTER (?a != \"Crypto Expo Asia\"^^<http://www.w3.org/2001/XMLSchema#string>)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	// Gabbi Simoni quan tâm đến sự kiện nào năm 2007
	public void Query15(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t\n";
	    queryString += "WHERE {";
	    queryString += "?per rdf:type label:Person .\n";
	    queryString += "?eve rdf:type label:Event .\n";
	    queryString += "?per label:label ?s .\n";
	    queryString += "?per ?p ?eve .\n";
	    queryString += " FILTER (?p = re:interested_in)";
	    queryString += "?eve ?q ?time .\n";
	    queryString += " FILTER (?q =re:at )";
	    queryString += "?eve label:label ?o .\n";
	    queryString += "?time label:label ?t .\n";
	    queryString += " FILTER regex(?t,\"2006\" )";
	    queryString += " FILTER regex (?s ,\"Gabbi Simoni\")";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}
	// Florentia Muino làm việc ở tổ chức nào vào thời gian nào?
	public void Query16(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t \n";
	    queryString += "WHERE { \n";
	    queryString += "  ?per rdf:type label:Person .\n";
	    queryString += "  ?per label:label ?s .\n";
		queryString += " FILTER regex(?s, \"Florentia Muino\") ";
	    queryString += "  ?org rdf:type label:Organization .\n";
		queryString += "  ?per ?p ?org .\n";
		queryString += " FILTER (?p =re:work_in)";
		queryString += "  ?org label:label ?o .\n";
		queryString += "  ?org re:at ?time .\n"; 
		queryString += "  ?time rdf:type label:Time .\n";
		queryString += "  ?time label:label ?t .\n";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}
	// Organization KFC mở chi nhánh ở đâu vào thời gian nào?
	public void Query17(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t \n";
	    queryString += "WHERE { \n";
//	    queryString += "  ?org rdf:type label:Organization .\n";
	    queryString += "  ?org label:label ?s .\n";
	    queryString += " FILTER regex(?s, \"KFC\") ";
//	    queryString += "  ?location rdf:type label:Location .\n";
		queryString += "  ?org ?p ?location .\n";
		queryString += " FILTER (?p =re:in)";
		queryString += "  ?location label:label ?o .\n";
		queryString += "  ?org re:at ?time .\n"; 
		queryString += "  ?time label:label ?t .\n";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}
	// Location Port Hedland, Australia có bao nhiêu tổ chức
	public void Query18(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
	    queryString += "WHERE {";
	    queryString += "?s rdf:type label:Location .\n";
	    queryString += "?s label:label ?name .\n";
	    queryString += " FILTER regex(?name, \"Port Hedland, Australia\")";
	    queryString += "?s re:have ?c .\n";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	// Korea có bao nhiêu sự kiện vào năm 2000
	public void Query19(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
	    queryString += "WHERE {";
	    queryString += "?s rdf:type label:Country .\n";
	    queryString += "?s label:label ?name .\n";
		queryString += " FILTER regex(?name, \"Korea\")";
	    queryString += "?s re:have ?event .\n";
	    queryString += "?event re:at ?time .\n";
	    queryString += "?time label:label ?t .\n";
		queryString += " FILTER regex(?t, \"2000\")";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}
	// International Exhibition VIETBUILD HANOI do ai tổ chức, người đó thuộc tổ chức nào, tổ chức đó thành lập năm bao nhiêu?
	public void Query20(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o ?a ?b ?c ?t\n";
	    queryString += "WHERE { \n";
	    queryString += " ?eve label:label ?s .\n";
		queryString += " ?eve ?p ?per .\n";
		queryString += " FILTER (?p = re:donated_by)";
		queryString += " ?per label:label ?o .\n";
		queryString += " ?per ?a ?org .\n";
		queryString += " FILTER (?a = re:work_in)";
		queryString += " ?org label:label ?b .\n";
		queryString += " ?org ?c ?time .\n";
		queryString += " FILTER (?c = re:at)";
		queryString += " ?time label:label ?t .\n";
		queryString += " FILTER regex(?s, \"International Exhibition VIETBUILD HANOI\") ";
		queryString += "}";
		queryString += " LIMIT 5";
		this.resultQUERY7(queryString, connection);
	}
	// Query 1 tham số
	private void resultQUERY1(String query, RepositoryConnection connection) {
		long startTime = System.currentTimeMillis();
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value o = bind.getValue("o");
			//System.out.format("%s\n",splitPrefix(o));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time query: " + (endTime - startTime));
	}
	// Query 3 tham số
	private void resultQUERY3(String query, RepositoryConnection connection) {
		long startTime = System.currentTimeMillis();
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			//System.out.format("s: %s p: %s o: %s\n",splitPrefix(s),splitPrefix(p),splitPrefix(o));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time query: " + (endTime - startTime));
	}
	// Query 4 tham số
	private void resultQUERY4(String query, RepositoryConnection connection) {
		long startTime = System.currentTimeMillis();
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			Value t = bind.getValue("t");
			//System.out.format("s: %s p: %s o: %s t: %s\n",splitPrefix(s),splitPrefix(p),splitPrefix(o),splitPrefix(t));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time query: " + (endTime - startTime));
	}
	// Query 7 tham số
	private void resultQUERY7(String query, RepositoryConnection connection) {
		long startTime = System.currentTimeMillis();
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			Value a = bind.getValue("a");
			Value b = bind.getValue("b");
			Value c = bind.getValue("c");
			Value t = bind.getValue("t");
//			System.out.format("s: %s p: %s o: %s a: %s b: %s c: %s t: %s\n",splitPrefix(s),splitPrefix(p),splitPrefix(o),splitPrefix(a),splitPrefix(b),splitPrefix(c),splitPrefix(t));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time query: " + (endTime - startTime));
	}
	private String splitPrefix(Value a) {
		if(a == null) return "";
		String b = a.toString();
		b = b.replace("http://www.example.org/ontology/", "");
		b = b.replace("http://www.example.org/person/", "");
		b = b.replace("http://www.example.org/organization/", "");
		b = b.replace("http://www.example.org/location/", "");
		b = b.replace("http://www.example.org/event/", "");
		b = b.replace("http://www.example.org/time/", "");
		b = b.replace("http://www.example.org/country/", "");
		b = b.replace("http://www.example.org/relationship/", "");
		b = b.replace("^^<http://www.w3.org/2001/XMLSchema#string>", "");
		b = b.replace("^^<http://www.w3.org/2001/XMLSchema#int>", "");
		b = b.replace("^^<http://www.w3.org/2001/XMLSchema#date>", "");
		b = b.replace("_", " ");
		b = b.replace("\"", "");
		return b;
	}
}