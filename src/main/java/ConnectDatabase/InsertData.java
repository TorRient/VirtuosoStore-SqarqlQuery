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
	private String NAMESPACE = "http://www.example.org/ontology/";

	// Bộ namespace chuẩn bị khởi tạo
	private String personNamespace = "http://www.example.org/person/";
	private String organizationNamespace = "http://www.example.org/organization/";
	private String locationNamespace = "http://www.example.org/location/";
	private String countryNamespace = "http://www.example.org/country/";
	private String timeNamespace = "http://www.example.org/time/";
	private String eventNamespace = "http://www.example.org/event/";
	private String relationshipNamespace = "http://www.example.org/relationship/";

	private RepositoryConnection connection = null;
	private ValueFactory valueFactory = null;

	private IRI LABEL;
	private IRI DESCRIPTION;
	private IRI LINK;
	private IRI DATE;
	private IRI AGE;
	private IRI JOB;
	private IRI HEADQUARTER;

	private IRI PERSONTYPE;
	private IRI ORGANIZATIONTYPE;
	private IRI LOCATIONTYPE;
	private IRI COUNTRYTYPE;
	private IRI TIMETYPE;
	private IRI EVENTTYPE;

	private Model model;

	public InsertData() {
		Repository myRepository1 = new VirtuosoRepository("jdbc:virtuoso://localhost:1111", "dba", "dba");
		connection = myRepository1.getConnection();

		valueFactory = connection.getValueFactory();
		LABEL = valueFactory.createIRI(NAMESPACE, "label");
		DESCRIPTION = valueFactory.createIRI(NAMESPACE, "description");
		LINK = valueFactory.createIRI(NAMESPACE, "extracted-link");
		DATE = valueFactory.createIRI(NAMESPACE, "extracted-date");
		AGE = valueFactory.createIRI(NAMESPACE, "age");
		JOB = valueFactory.createIRI(NAMESPACE, "job");
		HEADQUARTER = valueFactory.createIRI(NAMESPACE, "headquarter");

		PERSONTYPE = valueFactory.createIRI(NAMESPACE, "Person");
		ORGANIZATIONTYPE = valueFactory.createIRI(NAMESPACE, "Organization");
		LOCATIONTYPE = valueFactory.createIRI(NAMESPACE, "Location");
		COUNTRYTYPE = valueFactory.createIRI(NAMESPACE, "Country");
		TIMETYPE = valueFactory.createIRI(NAMESPACE, "Time");
		EVENTTYPE = valueFactory.createIRI(NAMESPACE, "Event");

		model = new TreeModel();
	}

	public RepositoryConnection getConnection() {
		return connection;
	}

	public Model getModel() {
		return model;
	}

	public void closeConnection() {
		if (connection != null) {
			connection.close();
		}
	}

	public void clear() {
		if (connection != null) {
			connection.clear();
		}
	}

	private IRI insertEntity(Entity entity, String nameSpace, IRI type) {
		IRI iri = valueFactory.createIRI(nameSpace, entity.getId());
		Literal label = valueFactory.createLiteral(entity.getLabel());
		Literal description = valueFactory.createLiteral(entity.getDescription());
		Literal extractedLink = valueFactory.createLiteral(entity.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(entity.getExtractedDate(), XMLSchema.DATE);

		model.add(iri, RDF.TYPE, type);
		model.add(iri, LABEL, label);
		model.add(iri, DESCRIPTION, description);
		model.add(iri, LINK, extractedLink);
		model.add(iri, DATE, extractedDate);
		return iri;
	}

	private IRI insertEntity(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(person.getExtractedDate(), XMLSchema.DATE);
		Literal age = valueFactory.createLiteral(person.getAge());
		Literal job = valueFactory.createLiteral(person.getJob());

		model.add(iri, RDF.TYPE, PERSONTYPE);
		model.add(iri, LABEL, label);
		model.add(iri, DESCRIPTION, description);
		model.add(iri, LINK, extractedLink);
		model.add(iri, DATE, extractedDate);
		model.add(iri, AGE, age);
		model.add(iri, JOB, job);
		return iri;
	}

	private IRI insertEntity(Organization organization) {
		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal description = valueFactory.createLiteral(organization.getDescription());
		Literal extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(organization.getExtractedDate(), XMLSchema.DATE);
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquater());

		model.add(iri, RDF.TYPE, ORGANIZATIONTYPE);
		model.add(iri, LABEL, label);
		model.add(iri, DESCRIPTION, description);
		model.add(iri, LINK, extractedLink);
		model.add(iri, DATE, extractedDate);
		model.add(iri, HEADQUARTER, headquarter);
		return iri;
	}

	public IRI insertEntity(Entity entity) {
		if (entity instanceof Person) {
			return insertEntity((Person) entity);
		} else if (entity instanceof Organization) {
			return insertEntity((Organization) entity);
		} else if (entity instanceof Location) {
			return insertEntity((Location) entity, locationNamespace, LOCATIONTYPE);
		} else if (entity instanceof Country) {
			return insertEntity((Country) entity, countryNamespace, COUNTRYTYPE);
		} else if (entity instanceof Time) {
			return insertEntity((Time) entity, timeNamespace, TIMETYPE);
		} else if (entity instanceof Event) {
			return insertEntity((Event) entity, eventNamespace, EVENTTYPE);
		}
		return null;
	}

	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}

	public void insertStatement(IRI entity1, IRI relationship, IRI entity2) {
		model.add(entity1, relationship, entity2);
	}

}
