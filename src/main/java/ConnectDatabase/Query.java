package ConnectDatabase;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import virtuoso.rdf4j.driver.VirtuosoRepository;

public class Query {
	Repository myRepository = new VirtuosoRepository("jdbc:virtuoso://localhost:1111","dba","dba");
	
	RepositoryConnection connection = myRepository.getConnection();
	public void Query1() {
		String queryString = "PREFIX ex: <http://www.randomlink.org/person/> \n";
		queryString += "PREFIX foaf: <" + FOAF.NAMESPACE + "> \n";
		queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
		queryString += "  ?s ?p ?o .\n";
		queryString += " FILTER ( ?p = rdf:type)";
		queryString += "}";
		this.QUERY(queryString);
	}
	public void Query2() {
	    String queryString = "PREFIX ex: <http://www.randomlink.org/person/> \n";
	    queryString += "PREFIX foaf: <" + FOAF.NAMESPACE + "> \n";
	    queryString += "SELECT ?s ?p ?o \n";
	    queryString += "WHERE { \n";
	    queryString += " ?s <http://www.randomlink.org/ontology/age> \"20\" \n";
	    queryString += "}";
	    this.QUERY(queryString);
	}
	public void QUERY(String query) {
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