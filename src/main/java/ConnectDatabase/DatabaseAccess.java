						package ConnectDatabase;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;
import virtuoso.rdf4j.driver.VirtuosoRepository;

public class DatabaseAccess {
	private static String ontologyNamespace = "http://www.randomlink.org/ontology/";
	

	//Bộ namespace chuẩn bị khởi tạo
	private static String personNamespace = "http://www.randomlink.org/person/";
	private static String organizationNamespace = "http://www.randomlink.org/organization/";
	private static String locationNamespace = "http://www.randomlink.org/location/";
	private static String countryNamespace = "http://www.randomlink.org/country/";
	private static String timeNamespace = "http://www.randomlink.org/time/";
	private static String eventNamespace = "http://www.randomlink.org/event/";
	private static String relationshipNamespace = "http://randomlink.org/relationship/";
	
	private RepositoryConnection connection = null;
	private ValueFactory valueFactory = null;
		
	private IRI labelOntology;
	private IRI descriptionOntology;
	private IRI extractedLinkOntology;
	private IRI extractedDateOntology;
	private IRI ageOntology;
	private IRI headquarterOntology;
	
	private IRI personType;
	private IRI organizationType;
	private IRI locationType;
	private IRI countryType;
	private IRI timeType;
	private IRI eventType;
	public DatabaseAccess() {
		Repository myRepository1 = new VirtuosoRepository("jdbc:virtuoso://localhost:1111","dba","dba");
		connection = myRepository1.getConnection();
//		connection.setNamespace(prefix, name);
		
		valueFactory =  connection.getValueFactory();
		labelOntology = valueFactory.createIRI(personNamespace, "label");
		descriptionOntology = valueFactory.createIRI(ontologyNamespace, "description");
		extractedLinkOntology = valueFactory.createIRI(ontologyNamespace, "extracted-link");
		extractedDateOntology = valueFactory.createIRI(ontologyNamespace, "extracted-date");
		ageOntology = valueFactory.createIRI(ontologyNamespace, "age");
		headquarterOntology = valueFactory.createIRI(ontologyNamespace, "headquarter");
		
		personType = valueFactory.createIRI(ontologyNamespace, "Person");
		organizationType = valueFactory.createIRI(ontologyNamespace, "Organization");
		locationType = valueFactory.createIRI(ontologyNamespace, "Location");
		countryType = valueFactory.createIRI(ontologyNamespace, "Country");
		timeType = valueFactory.createIRI(ontologyNamespace, "Time");
		eventType = valueFactory.createIRI(ontologyNamespace, "Event");
		
	}
	
	public RepositoryConnection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			connection.close();
		}
	}
	
	public void clear() {
		if(connection != null) {
			connection.clear();
		}
	}
	
	private IRI insertPerson(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(person.getExtractedDate(), XMLSchema.DATE);
		Literal age = valueFactory.createLiteral(person.getAge());
		
		connection.add(iri, RDF.TYPE, personType );
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		connection.add(iri, ageOntology, age);
		return iri;
	}
	
	private IRI insertOrganization(Organization organization) {
		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal description = valueFactory.createLiteral(organization.getDescription());
		Literal extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(organization.getExtractedDate(), XMLSchema.DATE);
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquarter());
		
		connection.add(iri, RDF.TYPE, organizationType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		connection.add(iri, headquarterOntology, headquarter);
		
		return iri;
	}
	
	private IRI insertLocation(Location location) {
		IRI iri = valueFactory.createIRI(locationNamespace, location.getId());
		Literal label = valueFactory.createLiteral(location.getLabel());
		Literal description = valueFactory.createLiteral(location.getDescription());
		Literal extractedLink = valueFactory.createLiteral(location.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(location.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, locationType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		
		return iri;
	}
	
	private IRI insertCountry(Country country) {
		IRI iri = valueFactory.createIRI(countryNamespace, country.getId());
		Literal label = valueFactory.createLiteral(country.getLabel());
		Literal description = valueFactory.createLiteral(country.getDescription());
		Literal extractedLink = valueFactory.createLiteral(country.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(country.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, countryType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		
		return iri;
	}
	
	private IRI insertTime(Time time) {
		IRI iri = valueFactory.createIRI(timeNamespace, time.getId());
		Literal label = valueFactory.createLiteral(time.getLabel());
		Literal description = valueFactory.createLiteral(time.getDescription());
		Literal extractedLink = valueFactory.createLiteral(time.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(time.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, timeType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		return iri;
	}
	
	private IRI insertEvent(Event event) {
		IRI iri = valueFactory.createIRI(eventNamespace, event.getId());
		Literal label = valueFactory.createLiteral(event.getLabel());
		Literal description = valueFactory.createLiteral(event.getDescription());
		Literal extractedLink = valueFactory.createLiteral(event.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(event.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, eventType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		
		return iri;
	}
	
	public IRI insertEntity(Entity entity) {
		if(entity instanceof Person) {
			return insertPerson((Person) entity); 
		}
		else if(entity instanceof Organization) {
			return insertOrganization((Organization) entity);
		}
		else if(entity instanceof Location) {
			return insertLocation((Location) entity);
		}
		else if(entity instanceof Country) {
			return insertCountry((Country) entity);
		}
		else if(entity instanceof Time) {
			return insertTime((Time) entity);
		}
		else if(entity instanceof Event) {
			return insertEvent((Event) entity);
		}
		return null;
	}
	
	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}
	
	public void insertStatement(IRI entity1, IRI relationship, IRI entity2) {
		connection.add(entity1, relationship, entity2);
	}
	
	public long querySPARQLTime(String queryString) {
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		long startTime = System.currentTimeMillis();
		
		TupleQueryResult result = tupleQuery.evaluate();
		
		long endTime = System.currentTimeMillis();
		result.close();
		return endTime - startTime;
	}
	
//	public long queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
//		long startTime = System.currentTimeMillis();
//		RepositoryResult<Statement> statements = connection.getStatements(subject, predicate, object, context);
//		long endTime = System.currentTimeMillis();
//		statements.close();
//		return endTime - startTime;
//	}

	public void queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
		RepositoryResult<Statement> statements = connection.getStatements(subject, predicate, object, context);
		Model model = QueryResults.asModel(statements);
		model.setNamespace("rdf",RDF.NAMESPACE);
		model.setNamespace("rdfs",RDFS.NAMESPACE);
		model.setNamespace("foaf",FOAF.NAMESPACE);
		model.setNamespace("ex", personNamespace + "1");
		Rio.write(model, System.out, RDFFormat.TURTLE);
	}
	public void printRows_3(String query) {
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
	
	public IRI getLabelOntology() {
		return labelOntology;
	}

	public IRI getDescriptionOntology() {
		return descriptionOntology;
	}

	public IRI getExtractedLinkOntology() {
		return extractedLinkOntology;
	}

	public IRI getExtractedDateOntology() {
		return extractedDateOntology;
	}

	public IRI getAgeOntology() {
		return ageOntology;
	}

	public IRI getHeadquarterOntology() {
		return headquarterOntology;
	}

	public IRI getPersonType() {
		return personType;
	}

	public IRI getOrganizationType() {
		return organizationType;
	}

	public IRI getLocationType() {
		return locationType;
	}

	public IRI getCountryType() {
		return countryType;
	}

	public IRI getTimeType() {
		return timeType;
	}

	public IRI getEventType() {
		return eventType;
	}
}	
