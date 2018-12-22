package DatabaseGeneration;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
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

public class DBGeneration {
	private String NAMESPACE = "http://www.example.org/";

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

	public DBGeneration() {
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

		PERSONTYPE = valueFactory.createIRI(NAMESPACE, "PERSONTYPE");
		ORGANIZATIONTYPE = valueFactory.createIRI(NAMESPACE, "ORGANIZATIONTYPE");
		LOCATIONTYPE = valueFactory.createIRI(NAMESPACE, "LOCATIONTYPE");
		COUNTRYTYPE = valueFactory.createIRI(NAMESPACE, "COUNTRYTYPE");
		TIMETYPE = valueFactory.createIRI(NAMESPACE, "TIMETYPE");
		EVENTTYPE = valueFactory.createIRI(NAMESPACE, "EVENTTYPE");
	}

	public RepositoryConnection getConnection() {
		return connection;
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

	private IRI addEntity(Entity entity, String nameSpace, IRI type, Model model) {
		IRI iri = valueFactory.createIRI(nameSpace, entity.getId());
		Literal label = valueFactory.createLiteral(entity.getLabel());
		Literal description = valueFactory.createLiteral(entity.getDescription());
		Literal extractedLink = valueFactory.createLiteral(entity.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(entity.getExtractedDate());

		model.add(iri, RDF.TYPE, type);
		model.add(iri, LABEL, label);
		model.add(iri, DESCRIPTION, description);
		model.add(iri, LINK, extractedLink);
		model.add(iri, DATE, extractedDate);
		return iri;
	}

	private IRI addEntity(Person person, Model model) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(person.getExtractedDate());
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

	private IRI addEntity(Organization organization,Model model) {
		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal description = valueFactory.createLiteral(organization.getDescription());
		Literal extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(organization.getExtractedDate());
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquater());

		model.add(iri, RDF.TYPE, ORGANIZATIONTYPE);
		model.add(iri, LABEL, label);
		model.add(iri, DESCRIPTION, description);
		model.add(iri, LINK, extractedLink);
		model.add(iri, DATE, extractedDate);
		model.add(iri, HEADQUARTER, headquarter);
		return iri;
	}

	public IRI addEntity(Entity entity,Model model) {
		if (entity instanceof Person) {
			return addEntity((Person) entity,model);
		} else if (entity instanceof Organization) {
			return addEntity((Organization) entity,model);
		} else if (entity instanceof Location) {
			return addEntity((Location) entity, locationNamespace, LOCATIONTYPE,model);
		} else if (entity instanceof Country) {
			return addEntity((Country) entity, countryNamespace, COUNTRYTYPE,model);
		} else if (entity instanceof Time) {
			return addEntity((Time) entity, timeNamespace, TIMETYPE,model);
		} else if (entity instanceof Event) {
			return addEntity((Event) entity, eventNamespace, EVENTTYPE,model);
		}
		return null;
	}

	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}

	public void insertStatement(IRI entity1, IRI relationship, IRI entity2, Model model) {
		model.add(entity1, relationship, entity2);
	}

}
