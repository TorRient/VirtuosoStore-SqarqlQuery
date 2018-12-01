package ConnectDatabase;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
 
public class Query {
	
	public void Query1(RepositoryConnection connection) {
		String queryString = "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "  ?s ?p ?o .\n";
		queryString += " FILTER ( ?p = <http://www.example.org/ontology/age> )";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	public void Query2(RepositoryConnection connection) {
		String queryString = "PREFIX age:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "  ?s age:age  ?o .\n";
		queryString += " FILTER ( ?o = 20)";
		queryString += "}";
		this.QUERY(queryString, connection);
	}
	public void Query3(RepositoryConnection connection) {
		String queryString = "PREFIX age:<http://www.example.org/ontology/> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "  ?s age:extracted-link ?o .\n";
		queryString += "}";
		this.QUERY(queryString, connection);
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
}