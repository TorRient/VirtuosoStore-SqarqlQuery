package query;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Query {

	// In tên Event 1
	public void Query1(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX event:<http://www.example.org/event/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE {";
		queryString += "?s rdf:type label:EVENTTYPE .\n";
		queryString += "?s ?p ?o .\n";
		queryString += " FILTER (?s = event:Event1 && ?p = label:label)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}

	// In ra mô tả về Organization1
	public void Query2(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX org:<http://www.example.org/organization/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE {";
		queryString += "?s rdf:type label:ORGANIZATIONTYPE .\n";
		queryString += "?s ?p ?o .\n";
		queryString += " FILTER (?s = org:Organization1 && ?p = label:description)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}

	// In ra định danh có tuổi bằng 20
	public void Query3(RepositoryConnection connection) {
		String queryString = "PREFIX age:<http://www.example.org/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += "  ?s rdf:type label:PERSONTYPE .\n";
		queryString += "  ?s ?p ?o .\n";
		queryString += " FILTER (?p = age:age && ?o = 20)";
		queryString += "}";
		queryString += " LIMIT 20";
		this.resultQUERY3(queryString, connection);
	}

	// In ra tên của Person1
	public void Query4(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { ?s ?p ?o .\n";
		queryString += " FILTER (?s =person:Person1 && ?p = label:label)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}

	// In ra tuổi person2 ?
	public void Query5(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?s rdf:type label:PERSONTYPE .\n";
		queryString += " ?s ?p ?o .\n";
		queryString += " FILTER ( ?s = person:Person2 && ?p = label:age)";
		queryString += "}";
		this.resultQUERY3(queryString, connection);
	}

	// Sư kiện Vietnam AutoExpo diễn ra tại đâu?
	public void Query6(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?event rdf:type label:EVENTTYPE .\n";
		queryString += " ?event ?p ?a .\n";
		queryString += " ?a rdf:type label:LOCATIONTYPE .\n";
		queryString += " ?event label:label ?s .\n";
		queryString += " ?a label:label ?o .\n";
		queryString += " FILTER (?s = \"Vietnam AutoExpo\"^^<http://www.w3.org/2001/XMLSchema#string>) ";
		queryString += " FILTER (?p =re:held_in)";
		queryString += "}";
		queryString += "LIMIT 1";
		this.resultQUERY3(queryString, connection);
	}

	// Location Port Hedland, Australia có bao nhiêu tổ chức
	public void Query7(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
		queryString += "WHERE {";
		queryString += "?s label:label ?name .\n";
		queryString += "?s rdf:type label:LOCATIONTYPE.\n";
		queryString += "?s re:have ?c .\n";
		queryString += " FILTER (?name = \"Port Hedland, Australia\"^^<http://www.w3.org/2001/XMLSchema#string>)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}

	// Đếm số người có mặt tại sự kiện Vietnam AutoExpo
	public void Query8(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
		queryString += "WHERE {";
		queryString += "?s ?p ?o .\n";
		queryString += " FILTER (?p = re:participate)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}

	// Andie Trapp là cha của ai?
	public void Query9(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?person label:label ?s .\n";
		queryString += " ?person ?p ?per .\n";
		queryString += " ?per label:label ?o .\n";
		queryString += " FILTER (?s = \"Claresta Pevie\"^^<http://www.w3.org/2001/XMLSchema#string>) ";
		queryString += " FILTER (?p =re:is_dad_of)";
		queryString += "}";
		queryString += " LIMIT 3";
		this.resultQUERY3(queryString, connection);
	}

	// Đếm số sư kiện xảy ra vào Oct. 27, 2009
	public void Query10(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT count(?s) as ?o \n";
		queryString += "WHERE {";
		queryString += "?s rdf:type label:EVENTTYPE .\n";
		queryString += "?b rdf:type label:TIMETYPE .\n";
		queryString += "?s ?p ?b .\n";
		queryString += "?b label:label ?a .\n";
		queryString += " FILTER (?a = \"Oct. 27, 2009\"^^<http://www.w3.org/2001/XMLSchema#string>)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}

	// Sự kiện Sugarex Vietnam do nhưng ai tài trợ?
	public void Query11(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?event rdf:type label:EVENTTYPE .\n";
		queryString += " ?event label:label ?s .\n";
		queryString += " ?event ?p ?per .\n";
		queryString += " ?per rdf:type label:PERSONTYPE .\n";
		queryString += " ?per label:label ?o .\n";
		queryString += " FILTER (?s = \"INTERDYE ASIA\"^^<http://www.w3.org/2001/XMLSchema#string> && ?p =re:donated_by) ";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}

	// Nêu 10 sự kiện ở Palmas, Brazil đã xảy ra.
	public void Query12(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o\n";
		queryString += "WHERE { \n";
		queryString += "  ?location rdf:type label:LOCATIONTYPE .\n";
		queryString += "  ?event rdf:type label:EVENTTYPE .\n";
		queryString += "  ?location label:label ?s .\n";
		queryString += "  ?location ?p ?event .\n";
		queryString += "  ?event label:label ?o .\n";
		queryString += " FILTER (?s = \"Palmas, Brazil\"^^<http://www.w3.org/2001/XMLSchema#string> && ?p =re:held) ";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}

	// Florentia Muino đã đến thăm những nước nào?
	public void Query13(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?person label:label ?s .\n";
		queryString += " ?person ?p ?country .\n";
		queryString += " ?country label:label ?o .\n";
		queryString += " FILTER (?s = \"Florentia Muino\"^^<http://www.w3.org/2001/XMLSchema#string>) ";
		queryString += " FILTER (?p =re:visit)";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}

	// Tìm 10 người đã từng làm việc tại Sony hoặc Nokia
	public void Query14(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?per label:label ?s .\n";
		queryString += " ?per ?p ?org .\n";
		queryString += " ?org label:label ?o .\n";
		queryString += " FILTER (?p = re:manage)";
		queryString += " FILTER (?o = \"Sony\"^^<http://www.w3.org/2001/XMLSchema#string> || ?o = \"Nokia\"^^<http://www.w3.org/2001/XMLSchema#string> )";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY3(queryString, connection);
	}

	// In ra những người tham dự sự kiện Vietnam Foodexpo nhưng không tham dự Crypto
	// Expo Asia
	public void Query15(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?o \n";
		queryString += "WHERE { \n";
		queryString += " ?b ?p ?s .\n";
		queryString += " ?b rdf:type label:PERSONTYPE .\n";
		queryString += " ?b label:label ?o .\n";
		queryString += " ?s label:label ?a .\n";
		queryString += " FILTER (?p = re:participate)";
		queryString += " FILTER (?a = \"Vietnam  Foodexpo\"^^<http://www.w3.org/2001/XMLSchema#string>)";
		queryString += " FILTER (?a != \"Crypto Expo Asia\"^^<http://www.w3.org/2001/XMLSchema#string>)";
		queryString += "}";
		this.resultQUERY1(queryString, connection);
	}

	// Edith Tardiff quan tâm đến sự kiện nào, sự kiện đó diễn ra tại năm nào?
	public void Query16(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t\n";
		queryString += "WHERE {";
		queryString += "?per label:label ?s .\n";
		queryString += "?per rdf:type label:PERSONTYPE .\n";
		queryString += "?per ?p ?eve .\n";
		queryString += "?eve rdf:type label:EVENTTYPE .\n";
		queryString += "?eve ?q ?time .\n";
		queryString += "?eve label:label ?o .\n";
		queryString += "?time label:label ?t .\n";
		queryString += " FILTER (?s = \"Edith Tardiff\"^^<http://www.w3.org/2001/XMLSchema#string> && ?p = re:interested_in && ?q =re:at)";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}

	// Florentia Muino đã làm việc ở tổ chức nào, tổ chức đó có trụ sở ở đâu?
	public void Query17(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t \n";
		queryString += "WHERE { \n";
		queryString += "  ?per label:label ?s .\n";
		queryString += "  ?per rdf:type label:PERSONTYPE .\n";
		queryString += "  ?per ?p ?org .\n";
		queryString += "  ?org rdf:type label:ORGANIZATIONTYPE .\n";
		queryString += "  ?org label:label ?o .\n";
		queryString += "  ?org re:establish_in ?location .\n";
		queryString += "  ?location label:label ?t .\n";
		queryString += " FILTER (?s = \"Florentia Muino\"^^<http://www.w3.org/2001/XMLSchema#string> && ?p =re:work_in) ";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}

	// Organization KFC mở chi nhánh ở đâu vào thời gian nào?
	public void Query18(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o ?t \n";
		queryString += "WHERE { \n";
		queryString += "  ?org rdf:type label:ORGANIZATIONTYPE .\n";
		queryString += "  ?org label:label ?s .\n";
		queryString += "  ?location rdf:type label:LOCATIONTYPE .\n";
		queryString += "  ?org ?p ?location .\n";
		queryString += "  ?location label:label ?o .\n";
		queryString += "  ?org re:establish_at ?time .\n";
		queryString += "  ?time label:label ?t .\n";
		queryString += " FILTER (?p =re:establish_in && ?s = \"KFC\"^^<http://www.w3.org/2001/XMLSchema#string>) ";
		queryString += "}";
		this.resultQUERY4(queryString, connection);
	}

	// Event INTERDYE ASIA do tổ chức nào chủ trì thời gian nào diễn ra ?
	public void Query19(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o ?t\n";
		queryString += "WHERE { \n";
		queryString += " ?event label:label ?s .\n";
		queryString += " ?event ?p ?org .\n";
		queryString += " ?event ?q ?time .\n";
		queryString += " ?org label:label ?o .\n";
		queryString += " ?time label:label ?t .\n";
		queryString += " FILTER (?s = \"INTERDYE ASIA\"^^<http://www.w3.org/2001/XMLSchema#string> && ?p =re:held_by && ?q = re:at) ";
		queryString += "}";
		queryString += " LIMIT 10";
		this.resultQUERY4(queryString, connection);
	}

	// Vietnam Foodexpo do ai tổ chức, người đó thuộc tổ
	// chức nào, tổ chức đó thành lập năm bao nhiêu?
	public void Query20(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX label:<http://www.example.org/> \n";
		queryString += "SELECT ?s ?p ?o ?a ?b ?c ?t\n";
		queryString += "WHERE { \n";
		queryString += " ?eve label:label ?s .\n";
		queryString += " ?eve ?p ?per .\n";
		queryString += " ?per label:label ?o .\n";
		queryString += " ?per ?a ?org .\n";
		queryString += " ?org label:label ?b .\n";
		queryString += " ?org ?c ?time .\n";
		queryString += " ?time label:label ?t .\n";
		queryString += " FILTER (?p = re:donated_by && ?a = re:work_in)";
		queryString += " FILTER (?s = \"Vietnam  Foodexpo\"^^<http://www.w3.org/2001/XMLSchema#string>) ";
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
			System.out.format("	%s\n", splitPrefix(o));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("	Time query: " + (endTime - startTime));
		result.close();
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
			System.out.format("	%s %s %s\n", splitPrefix(s), splitPrefix(p), splitPrefix(o));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("	Time query: " + (endTime - startTime));
		result.close();
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
			System.out.format("	%s %s %s %s\n", splitPrefix(s), splitPrefix(p), splitPrefix(o), splitPrefix(t));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("	Time query: " + (endTime - startTime));
		result.close();
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
			System.out.format("	%s %s %s %s %s %s %s\n", splitPrefix(s), splitPrefix(p), splitPrefix(o), splitPrefix(a),
					splitPrefix(b), splitPrefix(c), splitPrefix(t));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("	Time query: " + (endTime - startTime));
		result.close();
	}

	private String splitPrefix(Value a) {
		if (a == null)
			return "";
		String b = a.toString();
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
		b = b.replace("http://www.example.org/", "");
		b = b.replace("_", " ");
		b = b.replace("\"", "");
		return b;
	}
}