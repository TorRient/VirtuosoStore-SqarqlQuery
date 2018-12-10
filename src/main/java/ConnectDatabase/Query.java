package ConnectDatabase;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Query {

	// In ra tất cả dữ liệu
	public void Query1(RepositoryConnection connection) {
		String queryString = "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "?s ?p ?o .\n";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	// In ra những người có tuổi bằng 20
	public void Query2(RepositoryConnection connection) {
		String queryString = "PREFIX age:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "  ?s age:age  ?o .\n";
		queryString += " FILTER ( ?o = 20)";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	// In ra tên của Person1
	public void Query3(RepositoryConnection connection) {
		String queryString = "PREFIX label:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { ?s ?p ?o .\n";
//	    queryString +=  "person:Person1 ?p ?o .\n";
		queryString += "FILTER ( (?s = person:Person1 && ?p = re:Sinh_sống) ) .\n";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	public void Query4(RepositoryConnection connection) {
		String queryString = "PREFIX description:<http://www.example.org/ontology/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "SELECT ?o \n";
	    queryString += "WHERE { \n";
		queryString += " person:Person1 description:description ?o .\n";
		queryString += "}";
		this.QUERY3(queryString, connection);
	}
	public void QUERY(String query, RepositoryConnection connection) {
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			System.out.format("s: %s p: %s o: %s\n",s,p,o);
		}
	}
	public void Query5(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += " ?s ?p ?o .\n";
		queryString += " FILTER ( ?s = <http://www.example.org/person/Person2>)";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	public void Query6(RepositoryConnection connection) {
		String queryString = "PREFIX re:<http://www.example.org/relationship/> \n";
		queryString += "PREFIX person:<http://www.example.org/person/> \n";
		queryString += "select ?s ?p ?o \n";
		queryString +=	"where {\n";
		queryString +=	"?s ?p ?o.\n"; 
		queryString +=	"filter(!strstarts(str(?p), str(re:)))\n"; 
		queryString +=	"}";
		this.QUERY(queryString, connection);
	}
	public void QUERY3(String query, RepositoryConnection connection) {
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value o = bind.getValue("o");
			System.out.format("Tên của person1 là: %s\n",o);
		}
	}
}