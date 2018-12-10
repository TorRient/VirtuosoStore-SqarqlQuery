package ConnectDatabase;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;
import virtuoso.rdf4j.driver.VirtuosoRepository;

public class InsertData {
	private String ontologyNamespace = "http://www.example.org/ontology/";
	

	//Bộ namespace chuẩn bị khởi tạo
	private String personNamespace = "http://www.example.org/person/";
	private String organizationNamespace = "http://www.example.org/organization/";
	private String locationNamespace = "http://www.example.org/location/";
	private String countryNamespace = "http://www.example.org/country/";
	private String timeNamespace = "http://www.example.org/time/";
	private String eventNamespace = "http://www.example.org/event/";
	private String relationshipNamespace = "http://www.example.org/relationship/";
	
	private RepositoryConnection connection = null;
	private ValueFactory valueFactory = null;

	private IRI labelOntology;
	private IRI descriptionOntology;
	private IRI extractedLinkOntology;
	private IRI extractedDateOntology;
	private IRI ageOntology;
	
	private IRI personType;
	private IRI organizationType;
	private IRI locationType;
	private IRI countryType;
	private IRI timeType;
	private IRI eventType;
	
	private Model model;
	public InsertData() {
		Repository myRepository1 = new VirtuosoRepository("jdbc:virtuoso://localhost:1111","dba","dba");
		connection = myRepository1.getConnection();

		valueFactory =  connection.getValueFactory();
		labelOntology = valueFactory.createIRI(ontologyNamespace, "label");
		descriptionOntology = valueFactory.createIRI(ontologyNamespace, "description");
		extractedLinkOntology = valueFactory.createIRI(ontologyNamespace, "extracted-link");
		extractedDateOntology = valueFactory.createIRI(ontologyNamespace, "extracted-date");
		ageOntology = valueFactory.createIRI(ontologyNamespace, "age");
		
		personType = valueFactory.createIRI(ontologyNamespace, "Person");
		organizationType = valueFactory.createIRI(ontologyNamespace, "Organization");
		locationType = valueFactory.createIRI(ontologyNamespace, "Location");
		countryType = valueFactory.createIRI(ontologyNamespace, "Country");
		timeType = valueFactory.createIRI(ontologyNamespace, "Time");
		eventType = valueFactory.createIRI(ontologyNamespace, "Event");
		
		model = new TreeModel();
	}
	
	public RepositoryConnection getConnection() {
		return connection;
	}
	public Model getModel() {
		return model;
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
	private IRI insertEntity(Entity entity, String nameSpace, IRI type) {
		IRI iri = valueFactory.createIRI(nameSpace, entity.getId());
		Literal label = valueFactory.createLiteral(entity.getLabel());
		Literal description = valueFactory.createLiteral(entity.getDescription());
		Literal extractedLink = valueFactory.createLiteral(entity.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(entity.getExtractedDate(), XMLSchema.DATE);
		
		model.add(iri, RDF.TYPE, type );
		model.add(iri, labelOntology, label);
		model.add(iri, descriptionOntology, description);
		model.add(iri, extractedLinkOntology, extractedLink);
		model.add(iri, extractedDateOntology, extractedDate);
		return iri;
	}	
	
	private IRI insertEntity(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(person.getExtractedDate(), XMLSchema.DATE);
		Literal age = valueFactory.createLiteral(person.getAge());
		
		model.add(iri, RDF.TYPE, personType );
		model.add(iri, labelOntology, label);
		model.add(iri, descriptionOntology, description);
		model.add(iri, extractedLinkOntology, extractedLink);
		model.add(iri, extractedDateOntology, extractedDate);
		model.add(iri, ageOntology, age);
		return iri;
	}
	
//	private IRI insertEntity(Organization organization) {
//		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
//		Literal label = valueFactory.createLiteral(organization.getLabel());
//		Literal description = valueFactory.createLiteral(organization.getDescription());
//		Literal extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
//		Literal extractedDate = valueFactory.createLiteral(organization.getExtractedDate(), XMLSchema.DATE);
//		
//		model.add(iri, RDF.TYPE, organizationType);
//		model.add(iri, labelOntology, label);
//		model.add(iri, descriptionOntology, description);
//		model.add(iri, extractedLinkOntology, extractedLink);
//		model.add(iri, extractedDateOntology, extractedDate);
//		
//		return iri;
//	}
//	
//	private IRI insertLocation(Location location) {
//		IRI iri = valueFactory.createIRI(locationNamespace, location.getId());
//		Literal label = valueFactory.createLiteral(location.getLabel());
//		Literal description = valueFactory.createLiteral(location.getDescription());
//		Literal extractedLink = valueFactory.createLiteral(location.getExtractedLink());
//		Literal extractedDate = valueFactory.createLiteral(location.getExtractedDate(), XMLSchema.DATE);
//		
//		model.add(iri, RDF.TYPE, locationType);
//		model.add(iri, labelOntology, label);
//		model.add(iri, descriptionOntology, description);
//		model.add(iri, extractedLinkOntology, extractedLink);
//		model.add(iri, extractedDateOntology, extractedDate);
//		
//		return iri;
//	}
//	
//	private IRI insertCountry(Country country) {
//		IRI iri = valueFactory.createIRI(countryNamespace, country.getId());
//		Literal label = valueFactory.createLiteral(country.getLabel());
//		Literal description = valueFactory.createLiteral(country.getDescription());
//		Literal extractedLink = valueFactory.createLiteral(country.getExtractedLink());
//		Literal extractedDate = valueFactory.createLiteral(country.getExtractedDate(), XMLSchema.DATE);
//		
//		connection.add(iri, RDF.TYPE, countryType);
//		connection.add(iri, labelOntology, label);
//		connection.add(iri, descriptionOntology, description);
//		connection.add(iri, extractedLinkOntology, extractedLink);
//		connection.add(iri, extractedDateOntology, extractedDate);
//		
//		return iri;
//	}
//	
//	private IRI insertTime(Time time) {
//		IRI iri = valueFactory.createIRI(timeNamespace, time.getId());
//		Literal label = valueFactory.createLiteral(time.getLabel());
//		Literal description = valueFactory.createLiteral(time.getDescription());
//		Literal extractedLink = valueFactory.createLiteral(time.getExtractedLink());
//		Literal extractedDate = valueFactory.createLiteral(time.getExtractedDate(), XMLSchema.DATE);
//		
//		connection.add(iri, RDF.TYPE, timeType);
//		connection.add(iri, labelOntology, label);
//		connection.add(iri, descriptionOntology, description);
//		connection.add(iri, extractedLinkOntology, extractedLink);
//		connection.add(iri, extractedDateOntology, extractedDate);
//		return iri;
//	}
//	
//	private IRI insertEvent(Event event) {
//		IRI iri = valueFactory.createIRI(eventNamespace, event.getId());
//		Literal label = valueFactory.createLiteral(event.getLabel());
//		Literal description = valueFactory.createLiteral(event.getDescription());
//		Literal extractedLink = valueFactory.createLiteral(event.getExtractedLink());
//		Literal extractedDate = valueFactory.createLiteral(event.getExtractedDate(), XMLSchema.DATE);
//		
//		connection.add(iri, RDF.TYPE, eventType);
//		connection.add(iri, labelOntology, label);
//		connection.add(iri, descriptionOntology, description);
//		connection.add(iri, extractedLinkOntology, extractedLink);
//		connection.add(iri, extractedDateOntology, extractedDate);
//		
//		return iri;
//	}
	
	public IRI insertEntity(Entity entity) {
		if(entity instanceof Person) {
			return insertEntity((Person) entity); 
		}
		else if(entity instanceof Organization) {
			return insertEntity((Organization) entity,organizationNamespace,organizationType);
		}
		else if(entity instanceof Location) {
			return insertEntity((Location) entity,locationNamespace,locationType);
		}
		else if(entity instanceof Country) {
			return insertEntity((Country) entity,countryNamespace,countryType);
		}
		else if(entity instanceof Time) {
			return insertEntity((Time) entity,timeNamespace,timeType);
		}
		else if(entity instanceof Event) {
			return insertEntity((Event) entity,eventNamespace,eventType);
		}
		return null;
	}
	
	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}
	
	public void insertStatement(IRI entity1, IRI relationship, IRI entity2) {
		model.add(entity1, relationship, entity2);
	}
	
//	public long querySPARQLTime(String queryString) {
//		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
//		long startTime = System.currentTimeMillis();
//		
//		TupleQueryResult result = tupleQuery.evaluate();
//		
//		long endTime = System.currentTimeMillis();
//		result.close();
//		return endTime - startTime;
//	}
	
//	public long queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
//		long startTime = System.currentTimeMillis();
//		RepositoryResult<Statement> statements = connection.getStatements(subject, predicate, object, context);
//		long endTime = System.currentTimeMillis();
//		statements.close();
//		return endTime - startTime;
//	}
}	

